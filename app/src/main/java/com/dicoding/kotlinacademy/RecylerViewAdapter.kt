package com.dicoding.kotlinacademy

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list.view.*

/**
 * Created by root on 1/16/18.
 */
class RecylerViewAdapter(private val context: Context, private val items: List<Item>)
    : RecyclerView.Adapter<RecylerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val name: TextView = itemView.name
        private val image: ImageView = itemView.image

        fun bindItem(items: Item) {
            name.text = items.text
            Glide.with(itemView.context).load(items.image).into(image)
        }
    }
}