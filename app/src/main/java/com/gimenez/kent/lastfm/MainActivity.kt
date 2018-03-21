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



    val url = "https://www.last.fm/"
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


        progressbar.visibility = View.VISIBLE
        var input: String

        doAsync {

            input =editText.text.toString()

            val resultJson = URL(url+input).readText()
            val jsonObj= JSONObject(resultJson)
            val AlbumImage = jsonObj.getJSONObject("image").getString("#text")
            val Title = (jsonObj.getString("name"))
            val Artist = (jsonObj.getString("artist"))

            uiThread {


                Data= ArrayList()
                recyclerView.adapter = Adapter (Data)
                Data?.add(AlbumData(AlbumImage,Title,Artist))
            }
            progressbar.visibility = View.INVISIBLE


        }

    }
}
