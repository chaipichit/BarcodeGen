package com.example.barcodegender.second

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

import com.example.barcodegender.R
import com.example.barcodegender.adapter.NameListAdapter
import com.example.barcodegender.adapter.TypeListAdapter
import com.example.barcodegender.three.ThreeActivity
import com.example.barcodegender.three.ThreeFragment
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_type.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SecondFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {

    private var listPic = ArrayList<String>()
    private var listName = ArrayList<String>()
    private var listCode = ArrayList<String>()

    private val productListAdapter: NameListAdapter by lazy { NameListAdapter() }
    private var mDatabase: DatabaseReference? = null
    private var mMessageReference: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()
    }

    private fun initInstance() {
        initListView()

    }

    private fun initListView() {


        val code = activity?.intent?.getStringExtra("code")
        val name = activity?.intent?.getStringExtra("name")
        listPic = ArrayList()
        listName = ArrayList()
        listCode = ArrayList()


        productListAdapter.setListener(object : NameListAdapter.onItemClickListner {
            override fun onClick(name: String, get: String) {
                Log.d("WalksMan", name + "  " + get)

                val intent = Intent(context,ThreeActivity::class.java)
                intent.putExtra("code",code)
                intent.putExtra("name",name)
                startActivity(intent)
            }
        })
        mDatabase = FirebaseDatabase.getInstance().reference
        mMessageReference = FirebaseDatabase.getInstance().getReference("1")

        mDatabase!!.child("1").child("list").child(code.toString()).child("list")
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            SecondFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
