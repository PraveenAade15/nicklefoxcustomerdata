package com.example.nicketfoxcustomerlist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nicketfoxcustomerlist.R
import com.example.nicketfoxcustomerlist.infra.OnUserItemClicked
import com.example.nicketfoxcustomerlist.model.User
import kotlinx.android.synthetic.main.item_layout.view.*

class UserAdapterclass(
    private val list: List<User>,
    private val onUserClicked: OnUserItemClicked
) : RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return UserViewHolder(view, onUserClicked)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class UserViewHolder(
    private val itemView: View,
    private val onUserItemClicked: OnUserItemClicked
) : RecyclerView.ViewHolder(itemView) {

    fun setData(user: User) {
        itemView.apply {
            tvName.text = user.name
            tvEmail.text = user.email
            tvPhoneNumber.text = user.phone
            rating.numStars = user.rating!!.toInt()
        }
        itemView.item.setOnClickListener {
            onUserItemClicked.onClick(user)
        }
    }

}


