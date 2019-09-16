package com.example.barcodegender.detail

import android.app.ActionBar
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.barcodegender.R
import com.example.barcodegender.adapter.SizeAdapter
import com.example.barcodegender.api.HttpManager
import com.example.barcodegender.api.ProductSell
import com.example.barcodegender.api.SellCallBack
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.size_and_stock.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailFragment : Fragment() {
    private val productListAdapter: SizeAdapter by lazy { SizeAdapter() }
    private var listName = ArrayList<String>()
    private var listCost = ArrayList<String>()
    private var listPrice = ArrayList<String>()
    private var listSize = ArrayList<String>()
    private var listStock = ArrayList<Int>()
    private var listSell = ArrayList<Int>()
    private var barcode = ""
    private var listBarCode = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.save, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        listBarCode = ArrayList()
        if (item?.itemId == R.id.save) {
            var isType=true
            if (name_product.text.toString().isEmpty()){
                name_product.error = "กรุณากรอกชื่อก้าน"
                isType=false
            }
            if (name_short.text.toString().isEmpty()){
                name_short.error = "กรุณากรอกชื่อย่อ"
                isType=false
            }
            if (from.text.toString().isEmpty()){
                isType=false
                name_short.error="กรอกชื่อร้าน"
            }
            if (listPrice.size > 0&&isType) {
                for (i in listPrice.indices) {
                    listBarCode.add(barcode)
                    Log.d("Walksman", listBarCode.get(i))
                    if (i == listPrice.size - 1) {

                        val product = ProductSell()
                        product.BarCode = listBarCode
                        product.Price = listCost
                        product.PriceSell = listPrice
                        product.NumberSell = listSell
                        product.NumberStock = listStock
                        product.NameShop=from.text.toString()
                        product.NameAka = name_short.text.toString()
                        product.NameProduct = name_product.text.toString()
                        product.size = listSize
                        var call = HttpManager.getInstance().service.sell(product)
                        call.enqueue(object : Callback<SellCallBack> {
                            override fun onFailure(call: Call<SellCallBack>, t: Throwable) {
                                Log.d("WalksMan", "Fail 2")
                            }

                            override fun onResponse(
                                call: Call<SellCallBack>,
                                response: Response<SellCallBack>
                            ) {

                                if (response.isSuccessful) {
                                    Log.d("WalksMan", "Success")

                                    activity?.finish()
                                } else {

                                    Log.d("WalksMan", "Fail 1")

                                }
                            }
                        })

                    }
                }
            } else {
                Toast.makeText(context, "กรูณาเพิ่มสินค้าก่อนจ้า", Toast.LENGTH_SHORT).show()
            }
        }

        return super.onOptionsItemSelected(item)
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
        getBarCode()
    }

    private fun getBarCode() {

        barcode = activity?.intent?.getStringExtra("Barcode").toString()

        Log.d("WalksMan", barcode)

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
            dialog?.window?.setLayout((6 * width) / 7, ActionBar.LayoutParams.WRAP_CONTENT)
            dialog?.show()

            dialog?.tv_ok?.setOnClickListener {
                var iscancel = true
                if (checkBlankOrEmpty(dialog?.cost.text.toString())) {
                    dialog?.cost.error = "กรุณาใส่ราคาต้นทุน"
                    iscancel = false
                }
                if (checkBlankOrEmpty(dialog?.price.text.toString())) {
                    dialog?.price.error = "กรุณาใส่ราคาขาย"
                    iscancel = false
                }
                if (checkBlankOrEmpty(dialog?.size.text.toString())) {
                    dialog?.size.error = "กรุณาใส่ไซต์"
                    iscancel = false
                }
                if (checkBlankOrEmpty(dialog?.stock.text.toString())) {
                    dialog?.stock.error = "กรุณาใส่จำนวนstock"
                    iscancel = false
                }
                if (checkBlankOrEmpty(dialog?.sell.text.toString())) {
                    dialog?.sell.error = "กรุณาใส่จำนวนขาย"
                    iscancel = false
                }
                if (iscancel) {
                    listCost.add(dialog?.cost.text.toString())
                    listStock.add(dialog?.stock.text.toString().toInt())
                    listSize.add(dialog?.size.text.toString())
                    listSell.add(dialog?.sell.text.toString().toInt())
                    listPrice.add(dialog?.price.text.toString())
                    productListAdapter.addList(listCost, listPrice, listSell, listSize, listStock)
                    productListAdapter.notifyDataSetChanged()
                    dialog?.dismiss()
                }
            }

            dialog?.tv_cancel?.setOnClickListener {
                dialog?.dismiss()
            }
        }
    }

    private fun checkBlankOrEmpty(text: String): Boolean {
        if (text.isEmpty()) {
            return true
        } else {
            return false
        }

    }


    private fun initListView() {
        listName = ArrayList()
        listCost = ArrayList()
        listPrice = ArrayList()
        listSize = ArrayList()
        listStock = ArrayList()
        listSell = ArrayList()
        list_size?.layoutManager = LinearLayoutManager(context)
        list_size?.adapter = productListAdapter
        list_size.setHasFixedSize(true)
        productListAdapter.addList(listCost, listPrice, listSell, listSize, listStock)
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
