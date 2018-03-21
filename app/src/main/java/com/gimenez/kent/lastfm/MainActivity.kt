package com.gimenez.kent.lastfm

import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {



    val url = "https://ws.audioscrobbler.com/2.0/?method=album.search&album="
    val url2 = "&api_key=97f9c412d1ad4a2d1dedd7fbe169fb1d&format=json"
    private var Data : ArrayList<AlbumData>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView.layoutManager = LinearLayoutManager(this)


        editText.setOnEditorActionListener{ v, actionId, event ->

            if(actionId == EditorInfo.IME_ACTION_DONE){
                doSomething()
                true
            } else {
                false
            }
        }
    }

    private fun doSomething() {

        for (i in 0..10) {
            doAsync {

                val input = editText.text.toString()
                val resultJson = URL(url + input + url2).readText()
                val jsonObj = JSONObject(resultJson)

                val AlbumImage = jsonObj.getJSONObject("results").getJSONObject("albummatches").getJSONArray("album")
                        .getJSONObject(i).getJSONArray("image").getJSONObject(2).getString("#text")

                val Title = jsonObj.getJSONObject("results").getJSONObject("albummatches").getJSONArray("album")
                        .getJSONObject(i).getString("name")

                val Artist = jsonObj.getJSONObject("results").getJSONObject("albummatches").getJSONArray("album")
                        .getJSONObject(i).getString("artist")

                uiThread {


                    Data = ArrayList()
                    recyclerView.adapter = Adapter(Data)
                    Data?.add(AlbumData(AlbumImage, Title, Artist))
                }


            }

        }
    }
}
