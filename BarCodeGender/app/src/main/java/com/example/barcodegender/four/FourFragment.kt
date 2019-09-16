package com.example.barcodegender.four

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.barcodegender.FifthActivity

import com.example.barcodegender.R
import com.example.barcodegender.adapter.NameListAdapter
import com.example.barcodegender.three.ThreeActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_type.*


class FourFragment : Fragment() {


    private var listPic = ArrayList<String>()
    private var listName = ArrayList<String>()
    private var listCode = ArrayList<String>()

    private val productListAdapter: NameListAdapter by lazy { NameListAdapter() }
    private var mDatabase: DatabaseReference? = null
    private var mMessageReference: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_four, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()
    }

    companion object {
       @JvmStatic
        fun newInstance() =
            FourFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }


    private fun initInstance() {
        initListView()

    }

    private fun initListView() {


        val code = activity?.intent?.getStringExtra("code")
        val name = activity?.intent?.getStringExtra("name")
        val Barcode = activity?.intent?.getStringExtra("Barcode")

        listPic = ArrayList()
        listName = ArrayList()
        listCode = ArrayList()


        productListAdapter.setListener(object : NameListAdapter.onItemClickListner {
            override fun onClick(name: String, get: String) {
                Log.d("WalksMan", name + "  " + get)

                val intent = Intent(context, FifthActivity::class.java)
                intent.putExtra("Barcode",Barcode+get)
                intent.putExtra("code",code)
                intent.putExtra("name",name)
                startActivity(intent)
            }
        })
        mDatabase = FirebaseDatabase.getInstance().reference
        mMessageReference = FirebaseDatabase.getInstance().getReference("1")

        mDatabase!!.child("1").child("list2")
            .addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {

                    Log.d("WalksMan", p0.childrenCount.toString())

                    var i = 0
                    for (p1 in p0.getChildren()) {
                        i++
                        listPic.add(p1.value.toString())

                        listName.add(p1.key.toString())
                        listCode.add(p1.value.toString())
                        listPic.add(p1.child("name").value.toString())
                        if (i == p0.childrenCount.toInt()) {


                            Log.d("WalksMan", "Maneiei" + listName.size.toString())
                            list_type?.layoutManager = GridLayoutManager(context, 4)
                            list_type?.adapter = productListAdapter
                            productListAdapter.addList(listName, listPic, listCode)
                            productListAdapter.notifyDataSetChanged()
                        }
                    }


                }


            })
        mDatabase!!.child("1").parent


    }
}
