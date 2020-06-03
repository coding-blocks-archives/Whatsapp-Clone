package com.puldroid.whatsappclone.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.puldroid.whatsappclone.R
import com.puldroid.whatsappclone.models.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class UsersAdapter() :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    private val list: MutableList<User> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    fun addItems(newList: MutableList<User>) {
        list.addAll(newList)
        notifyItemRangeInserted(list.size - newList.size, list.size)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) = with(itemView) {
            countTv.isVisible = false
            timeTv.isVisible = false

            titleTv.text = user.name
            subTitleTv.text = user.status
            Picasso.get()
                .load(user.thumbImage)
                .placeholder(R.drawable.defaultavatar)
                .error(R.drawable.defaultavatar)
                .into(userImgView)
        }
    }
}