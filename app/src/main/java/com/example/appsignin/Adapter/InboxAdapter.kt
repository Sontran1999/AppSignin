package com.example.appsignin.Adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appsignin.Object.Inbox
import com.example.appsignin.R
import kotlinx.android.synthetic.main.adapter_inbox.view.*

class InboxAdapter(): RecyclerView.Adapter<InboxAdapter.ViewHoder>() {
    private var  mInbox: ArrayList<Inbox> = arrayListOf()


    class ViewHoder(itemView: View): RecyclerView.ViewHolder(itemView){
        var avatar: ImageView =itemView.findViewById(R.id.img_avatar)
        var name: TextView= itemView.findViewById(R.id.tv_name)
        var message: TextView= itemView.findViewById(R.id.tv_message)
        var notification: TextView= itemView.findViewById(R.id.tv_notification)
        var dateTime: TextView= itemView.findViewById(R.id.tv_datetime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inboxView: View = layoutInflater.inflate(R.layout.adapter_inbox, parent, false)
        var viewHoder: ViewHoder= ViewHoder(inboxView)
        return viewHoder
    }

    override fun getItemCount(): Int {
        return mInbox.size
    }

    override fun onBindViewHolder(holder: ViewHoder, position: Int) {

        holder.avatar.setImageResource(mInbox[position].avatar)
        holder.name.text= mInbox[position].name
        holder.message.text= mInbox[position].message
        holder.notification.text= mInbox[position].notification
        holder.dateTime.text= mInbox[position].dateTime

    }
    fun setList(list:ArrayList<Inbox>){
        this.mInbox=list
        notifyDataSetChanged()
    }
}