package com.example.barcodegender.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.barcodegender.R
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.android.synthetic.main.name.view.*


class NameListAdapter : RecyclerView.Adapter<NameItemView>() {


    private var listName: ArrayList<String>? = null
    private var listPic: ArrayList<String>? = null
    private var listCode: ArrayList<String>? = null
    private var listener: onItemClickListner? = null

    interface onItemClickListner {
        fun onClick(name: String, get: String)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameItemView {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.name, parent, false)
        return NameItemView(view)
    }

    override fun onBindViewHolder(holder: NameItemView, position: Int) {

        holder.setName(listName?.get(position)!!)
        holder.itemView.item_name.setOnClickListener {

            listener?.onClick(listName?.get(position)!!,listCode?.get(position)!!)
        }

    }


    override fun getItemCount(): Int {
        return listName?.size!!
    }

    fun addList(
        listName: ArrayList<String>,
        listPic: ArrayList<String>,
        listCode: ArrayList<String>
    ) {
        this.listName = listName
        this.listPic = listPic
        this.listCode=listCode
    }

    fun setListener(listener: onItemClickListner) {
        this.listener = listener

    }


}