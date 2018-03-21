package com.gimenez.kent.lastfm

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_row.view.*

/**
 * Created by Kent on 3/21/2018.
 */
class Adapter ( val data: ArrayList<AlbumData>?):RecyclerView.Adapter<Adapter.CustomViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int):
        CustomViewHolder = CustomViewHolder (LayoutInflater.from(parent?.context).inflate(R.layout.album_row,parent,false))


    override fun getItemCount(): Int = data!!.size


    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
            val Data = data!![position]
            holder?.Title?.text = Data.name
            holder?.Singer?.text = Data.artist
            Picasso.with(holder?.itemView?.context).load(Data.image).into(holder?.Album)


    }


    class CustomViewHolder (view: View) : RecyclerView.ViewHolder(view){
                        val Album = view.ivAlbum
                        val Title = view.tvTitle
                        val Singer = view.tvSinger

    }

}





