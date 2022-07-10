package com.example.nicketfoxcustomerlist.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nicketfoxcustomerlist.R
import com.example.nicketfoxcustomerlist.databinding.FragmentHomeBinding
import com.example.nicketfoxcustomerlist.infra.OnUserItemClicked
import com.example.nicketfoxcustomerlist.model.User
import com.example.nicketfoxcustomerlist.utils.SnackBarUtil
import com.example.nicketfoxcustomerlist.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment(), OnUserItemClicked {

    private var userList = mutableListOf<User>()

    private val viewModel: UserViewModel by viewModels()


    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!

    lateinit var snackBarUtil: SnackBarUtil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.adapter = UserAdapterclass(userList, this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        getDataFromDB()
        getSort()
//        swipItempDelete()
    }

    //swipe function item can delete from list

//    private fun swipItempDelete() {
//        val swipeToDeleteItem=object :SwapToDeleteItem(){
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val position=viewHolder.adapterPosition
//                userList.removeAt(position)
//                recyclerView.adapter?.notifyItemRemoved(position)
//            }
//
//        }
//        val itemTouchHelper=ItemTouchHelper(swipeToDeleteItem)
//        itemTouchHelper.attachToRecyclerView(recyclerView)
//    }

    //update all item observe

    private fun getDataFromDB() {
        viewModel.getAllMoney().observe(viewLifecycleOwner, {
            userList.clear()
            userList.addAll(it)
            recyclerView.adapter?.notifyDataSetChanged()
        })
    }

    // sort item in list with name

    private fun getSort() {
        binding.sort.setOnClickListener {
            viewModel.getAllMoney().observe(viewLifecycleOwner, {
                userList.clear()
                userList.addAll(it)
                userList.sortBy {
                    it.name
                }
                recyclerView.adapter?.notifyDataSetChanged()
            })
        }
    }

//item click delete from here

    override fun onClick(user: User) {
        val alertDialog = AlertDialog.Builder(context).create()
        val customLayout = layoutInflater.inflate(R.layout.alert_dialog_view, null)
        alertDialog.setView(customLayout)
        alertDialog.show()
        displaySnackBarUtil("Customer list is clicked")
        val mBtnEdit = customLayout.findViewById<Button>(R.id.btnEdit)
        val mBtnDelete = customLayout.findViewById<Button>(R.id.btnDelete)
        mBtnEdit.setOnClickListener {
            val intent = Intent(context, AddDetailsActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
            alertDialog.dismiss()
        }

        mBtnDelete.setOnClickListener {
            viewModel.deleteUser(user)
            alertDialog.dismiss()
            displaySnackBarUtil("Customer deleted")
        }
    }

    // sneak bar error handling

    private fun displaySnackBarUtil(message: String) {
        snackBarUtil = SnackBarUtil(requireContext(), binding.root, message)
    }
}