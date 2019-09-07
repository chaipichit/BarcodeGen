package com.example.barcodegender.detail

import android.app.ActionBar
import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.barcodegender.R
import com.example.barcodegender.adapter.NameListAdapter
import com.example.barcodegender.adapter.SizeAdapter
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_type.*
import android.util.DisplayMetrics




class DetailFragment : Fragment() {
    private val productListAdapter: SizeAdapter by lazy { SizeAdapter() }
    private var listName = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()
    }

    private fun initInstance() {
        initListView()
        initButton()
    }

    private fun initButton() {
        add_item.setOnClickListener {

            val metrics = resources.displayMetrics
            val width = metrics.widthPixels
            val height = metrics.heightPixels

            var dialog = context?.let { it1 -> Dialog(it1) }
            dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog?.setContentView(R.layout.size_and_stock)
            dialog?.setCancelable(false)
            dialog?.window?.setLayout((6 * width)/7, ActionBar.LayoutParams.WRAP_CONTENT)
            dialog?.show()

            //listName.add("Man")
            productListAdapter.notifyDataSetChanged()
        }

    }

    private fun initListView() {
        listName = ArrayList()
        list_size?.layoutManager = LinearLayoutManager(context)
        list_size?.adapter = productListAdapter
        list_size.setHasFixedSize(true)
        productListAdapter.addList(listName)
        productListAdapter.notifyDataSetChanged()


    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DetailFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
