package com.example.appsignin.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.appsignin.Fragment.InboxFragment
import com.example.appsignin.HomeActivity
import com.example.appsignin.Interface.OnItemClick
import com.example.appsignin.Object.Home
import com.example.appsignin.R

class HomeAdapter() : RecyclerView.Adapter<HomeAdapter.ViewHoder>() {
    lateinit var mContext: Context
    lateinit var mHome: ArrayList<Home>
    lateinit var onItemClick: OnItemClick

    constructor(mContext: Context, mHome: ArrayList<Home>, onItemClick: OnItemClick) : this() {
        this.mContext = mContext
        this.mHome = mHome
        this.onItemClick = onItemClick
    }

    class ViewHoder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var avatar: ImageView = itemView.findViewById(R.id.img_avatar)
        var name: TextView = itemView.findViewById(R.id.tv_name)
        var date: TextView = itemView.findViewById(R.id.tv_date)
        var status: TextView = itemView.findViewById(R.id.tv_status)
        var image: ImageView = itemView.findViewById(R.id.img_image)
        var like: CheckBox = itemView.findViewById(R.id.cb_like)
        var cmt: CheckBox = itemView.findViewById(R.id.cb_cmt)
        var money: TextView = itemView.findViewById(R.id.tv_money)
    }

    override fun getItemCount(): Int {
        return mHome.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoder {
        val layoutInflater = LayoutInflater.from(mContext)
        val homeView: View = layoutInflater.inflate(R.layout.adapter_home, parent, false)
        var viewHoder: ViewHoder = ViewHoder(homeView)
        return viewHoder
    }

    override fun onBindViewHolder(holder: ViewHoder, position: Int) {
        holder.avatar.setImageResource(mHome[position].avatar)
        holder.name.text = mHome[position].name
        holder.date.text = mHome[position].date
        holder.status.text = mHome[position].status
        mHome[position].image?.let {
            holder.image.setImageResource(it)
        }
//        holder.like.setBackgroundResource(mHome[position].like)
//        holder.cmt.setBackgroundResource(mHome[position].cmt)
        holder.money.text = mHome[position].money

        holder.avatar.setOnClickListener {
            onItemClick.onClicks(mHome[position])
        }

    }


}


