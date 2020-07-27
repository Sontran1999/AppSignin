package com.example.appsignin.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appsignin.Adapter.InboxAdapter
import com.example.appsignin.Database.UserRoomDatabase
import com.example.appsignin.HomeActivity
import com.example.appsignin.Object.Home
import com.example.appsignin.Object.Inbox
import com.example.appsignin.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext

class InboxFragment() : Fragment() {
    var mInboxDB: UserRoomDatabase?=null
    var arrayList: ArrayList<Inbox> = ArrayList()
    var adapterInbox: InboxAdapter = InboxAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_inbox, container, false)
        recycler(view)
        return view

    }

    fun recycler(view: View) {
        var recyclerView: RecyclerView = view.findViewById(R.id.recyclerView2)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.context)

//        arrayList.add(Inbox(R.drawable.avatar2,"Pearl Myers","xinchao","1","14:25pm"))
//        arrayList.add(Inbox(R.drawable.avatar3,"Gary Rose","xinchao","1","14:25pm"))
//
//       if(listHome != null){
//           var indexx:Int? = null
//           listHome!!.forEachIndexed { index, home ->
//               arrayList.forEachIndexed { index1, inbox ->
//                   if(home.name.equals(inbox.name)){
//                       indexx=index1
//                   }
//               }
//               if(indexx != null){
//                   arrayList.removeAt(indexx!!)
//               }
//               arrayList.add(0,Inbox(home.avatar,home.name,"xinchao","1",sdf2.format(date).toString()))
//           }
//       }

//        var activity = activity
//        if (activity is HomeActivity){
//            listHome?.let { activity.passDataToChild(it) }
//        }

        mInboxDB = UserRoomDatabase.getDatabase(activity as HomeActivity)
        arrayList= mInboxDB?.getDao()?.getAllInbox() as ArrayList<Inbox>
        recyclerView.adapter = adapterInbox
        adapterInbox.setList(arrayList)

    }


}