package com.example.barcodegender.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.barcodegender.R


class ProductItemView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var name: TextView? = null
    var pic: ImageView? = null


    init {
        name = itemView.findViewById(R.id.name_product)
        pic = itemView.findViewById(R.id.pic_product)

    }

    fun setName(get: String) {
        name?.text = get

    }

    fun setPic(get: String, context: Context) {
        pic?.let {
            Glide.with(context)
                .load(get).into(it)
        }
    }
}