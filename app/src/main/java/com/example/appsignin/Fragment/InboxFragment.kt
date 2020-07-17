package com.example.appsignin.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appsignin.Adapter.InboxAdapter
import com.example.appsignin.HomeActivity
import com.example.appsignin.Object.Home
import com.example.appsignin.Object.Inbox
import com.example.appsignin.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class InboxFragment( ): Fragment() {
    var listHome:ArrayList<Home>?=null
    constructor(listHome: ArrayList<Home>?):this(){
        this.listHome = listHome
    }
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
        var date: Date= Date()
        var sdf2= SimpleDateFormat("ht5   l:mm")
        var arrayList: ArrayList<Inbox> = ArrayList()


        arrayList.add(Inbox(R.drawable.avatar2,"Pearl Myers","xinchao","1","14:25pm"))
        arrayList.add(Inbox(R.drawable.avatar3,"Gary Rose","xinchao","1","14:25pm"))


       if(listHome != null){
           var indexx:Int? = null
           listHome!!.forEachIndexed { index, home ->
               arrayList.forEachIndexed { index1, inbox ->
                   if(home.name.equals(inbox.name)){
                       indexx=index1
                   }
               }
               if(indexx != null){
                   arrayList.removeAt(indexx!!)
               }
               arrayList.add(0,Inbox(home.avatar,home.name,"xinchao","1",sdf2.format(date).toString()))
           }
       }





        var activity = activity
        if (activity is HomeActivity){
            listHome?.let { activity.passDataToChild(it) }
        }


        var adapterInbox: InboxAdapter = InboxAdapter(view.context,arrayList)
        recyclerView.adapter= adapterInbox

    }


}