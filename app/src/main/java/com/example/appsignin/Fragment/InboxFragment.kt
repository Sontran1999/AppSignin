package com.example.appsignin.Fragment

import android.os.Bundle
import android.provider.Telephony
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appsignin.Adapter.InboxAdapter
import com.example.appsignin.Object.Inbox
import com.example.appsignin.R

class InboxFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View= inflater.inflate(R.layout.fragment_inbox, container, false)
        recycler(view)
        return view


    }

    fun recycler(view: View){
        var recyclerView: RecyclerView = view.findViewById(R.id.recyclerView2)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(view.context)

        var arrayList: ArrayList<Inbox> = ArrayList()

        arrayList.add(Inbox(R.drawable.avatar1,"son","xinchao","1","14:25pm"))

        var adapterInbox: InboxAdapter = InboxAdapter(view.context,arrayList)
        recyclerView.adapter= adapterInbox

    }
}