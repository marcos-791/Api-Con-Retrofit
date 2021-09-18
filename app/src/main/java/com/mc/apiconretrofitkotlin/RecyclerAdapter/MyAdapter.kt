package com.mc.apiconretrofitkotlin.RecyclerAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mc.apiconretrofitkotlin.ApiAdapter.Post
import com.mc.apiconretrofitkotlin.R

class MyAdapter (val context: Context, private val lista : ArrayList<Post>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.models,
            parent,false)
        return MyViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        //recupera los valores de cada elemento de la lista
        val post = lista[position]

        holder.userId.setText( ""+post.id)
        holder.userTitle.setText( post.title)
        holder.userBody.setText( post.body)

    }

    override fun getItemCount(): Int {
        return lista.size
    }


    //clase ViewHolder con los valores para el layout models
    class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){
        val userId : TextView = itemview.findViewById(R.id.userId)
        val userTitle : TextView = itemview.findViewById(R.id.userTitle)
        val userBody : TextView = itemview.findViewById(R.id.userBody)

    }
}