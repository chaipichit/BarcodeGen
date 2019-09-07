package com.example.barcodegender.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.barcodegender.R
import kotlinx.android.synthetic.main.item.view.*


class SizeAdapter : RecyclerView.Adapter<SizeItemView>() {


    private var listName: ArrayList<String>? = null
    private var listPic: ArrayList<String>? = null
    private var listCode: ArrayList<String>? = null
    private var listener: onItemClickListner? = null

    interface onItemClickListner {
        fun onClick(name: String, get: String)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeItemView {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.size_and_stock, parent, false)
        return SizeItemView(view)
    }

    override fun onBindViewHolder(holder: SizeItemView, position: Int) {


    }


    override fun getItemCount(): Int {
        return listName?.size!!
    }

    fun addList(
        listName: ArrayList<String>

    ) {
        this.listName = listName

    }

    fun setListener(listener: onItemClickListner) {
        this.listener = listener

    }


}