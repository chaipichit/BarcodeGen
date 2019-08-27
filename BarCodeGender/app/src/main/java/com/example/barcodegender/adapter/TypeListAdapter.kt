package com.example.barcodegender.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.barcodegender.R


class TypeListAdapter : RecyclerView.Adapter<ProductItemView>() {


    private var listName: ArrayList<String>? = null
    private var listPic: ArrayList<String>? = null

    private var listener: onItemClickListner? = null

    interface onItemClickListner {
        fun onClick(name: String)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemView {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ProductItemView(view)
    }

    override fun onBindViewHolder(holder: ProductItemView, position: Int) {

        holder.setName(listName?.get(position)!!)
        holder.setPic(listPic?.get(position)!!, holder.itemView.context)
//        holder.itemView.item.setOnClickListener {
//
//            listener?.onClick(listName?.get(position)!!)
//        }

    }


    override fun getItemCount(): Int {
        return listName?.size!!
    }

    fun addList(listName: ArrayList<String>, listPic: ArrayList<String>) {
        this.listName = listName
        this.listPic = listPic
    }

    fun setListener(listener: onItemClickListner) {
        this.listener = listener

    }


}