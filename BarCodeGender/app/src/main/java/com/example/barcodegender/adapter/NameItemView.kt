package com.example.barcodegender.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.barcodegender.R


class NameItemView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var name: TextView? = null


    init {
        name = itemView.findViewById(R.id.name)

    }

    fun setName(get: String) {
        name?.text = get

    }

}