package com.example.barcodegender.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.barcodegender.R
import kotlinx.android.synthetic.main.size_and_stock_text.view.*


class SizeAdapter : RecyclerView.Adapter<SizeItemView>() {


    private var listCost = ArrayList<String>()
    private var listPrice = ArrayList<String>()
    private var listSize = ArrayList<String>()
    private var listStock = ArrayList<Int>()
    private var listSell = ArrayList<Int>()
    private var listener: onItemClickListner? = null

    interface onItemClickListner {
        fun onClick(name: String, get: String)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeItemView {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.size_and_stock_text, parent, false)
        return SizeItemView(view)
    }

    override fun onBindViewHolder(holder: SizeItemView, position: Int) {

        holder.itemView.cost.text=listCost.get(position)
        holder.itemView.price.text=listPrice.get(position)
        holder.itemView.size.text=listSize.get(position)
        holder.itemView.stock.text=listStock.get(position).toString()
        holder.itemView.sell.text=listSell.get(position).toString()



    }


    override fun getItemCount(): Int {
        return listCost?.size!!
    }

    fun addList(
        listCost: ArrayList<String>,
        listPrice: ArrayList<String>,
        listSell: ArrayList<Int>,
        listSize: ArrayList<String>,
        listStock: ArrayList<Int>

    ) {
        this.listCost = listCost
        this.listPrice = listPrice
        this.listSell=listSell
        this.listSize=listSize
        this.listStock=listStock

    }

    fun setListener(listener: onItemClickListner) {
        this.listener = listener

    }


}