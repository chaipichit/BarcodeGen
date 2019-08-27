package com.example.barcodegender

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.barcodegender.adapter.TypeListAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_type.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TypeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TypeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TypeFragment : Fragment() {
    private var listPic = ArrayList<String>()
    private var listName = ArrayList<String>()
    private val productListAdapter: TypeListAdapter by lazy { TypeListAdapter() }
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_type, container, false)
    }
    private fun initListView() {
        listPic = ArrayList()
        listName = ArrayList()

        mDatabase = FirebaseDatabase.getInstance().reference
        mMessageReference = FirebaseDatabase.getInstance().getReference("1")

        mDatabase!!.child("1").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {

                Log.d("WalksMan", p0.childrenCount.toString())

                var i = 0
                for (p1 in p0.getChildren()) {
                    i++
                    //  listPic.add(p1.value.toString())

                    listName.add(p1.key.toString())

                    listPic.add(p1.child("pic").value.toString())
                    if (i == p0.childrenCount.toInt()) {


                        Log.d("WalksMan", "Maneiei" + listName.size.toString())
                        list_type?.layoutManager = GridLayoutManager(context, 4)
                        list_type?.adapter = productListAdapter
                        productListAdapter.addList(listName, listPic)
                        productListAdapter.notifyDataSetChanged()
                    }
                }


            }


        })
        mDatabase!!.child("1").parent





    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TypeFragment.
         */
         @JvmStatic
        fun newInstance() =
            TypeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
