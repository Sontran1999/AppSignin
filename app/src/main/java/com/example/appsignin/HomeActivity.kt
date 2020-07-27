package com.example.appsignin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appsignin.Adapter.HomeAdapter
import com.example.appsignin.Database.User
import com.example.appsignin.Database.UserRoomDatabase
import com.example.appsignin.Fragment.HomeFragment
import com.example.appsignin.Fragment.InboxFragment
import com.example.appsignin.Interface.OnItemClick
import com.example.appsignin.Object.Home
import com.example.appsignin.Object.Inbox
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext

class HomeActivity() : AppCompatActivity(), View.OnClickListener, OnItemClick, CoroutineScope {
    private var mInboxDB: UserRoomDatabase? = null
    private lateinit var mJob: Job
    val listFragment = arrayListOf(HomeFragment(), InboxFragment())
    private var fragmentManager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var ac: ActionBar? = supportActionBar
        if (ac != null) {
            ac.hide()
        }

        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        listFragment.forEachIndexed { index, fragment ->
            fragmentTransaction.add(R.id.frameContent, fragment)
            fragmentTransaction.hide(fragment)
        }
        fragmentTransaction.show(listFragment[0])
        btn_home.setBackgroundResource(R.drawable.ic_home)
        fragmentTransaction.commit()

        btn_home.setOnClickListener(this)
        btn_inbox.setOnClickListener(this)

        mJob = Job()

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_home -> {
                viewFragment(listFragment[0])
                btn_home.setBackgroundResource(R.drawable.ic_home)
                btn_inbox.setBackgroundResource(R.drawable.ic_group_8)
            }
            R.id.btn_inbox -> {
                viewFragment(listFragment[1])
                btn_inbox.setBackgroundResource(R.drawable.ic_inbox)
                btn_home.setBackgroundResource(R.drawable.ic_group_7)
            }
        }
    }

    fun viewFragment(fragment: Fragment) {
        var fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        if (fragment.isAdded) {
            fragmentTransaction.show(fragment)
            listFragment.forEach {
                if (it != fragment) fragmentTransaction.hide(it)
            }
        }
//        else {
//            fragmentTransaction.add(R.id.frameContent, fragment)
//        }
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

//    fun passDataToChild(listHome:ArrayList<Home>){
//
//        this.listHome =listHome
//    }

    override fun onClicks(home: Home) {
        var date: Date = Date()
        var sdf2 = SimpleDateFormat("hh:mm")
        var indexx: Int = -1
        mInboxDB = UserRoomDatabase.getDatabase(this)
        when {
            listFragment[0].isVisible -> {
                viewFragment(listFragment[1])
                btn_inbox.setBackgroundResource(R.drawable.ic_inbox)
                btn_home.setBackgroundResource(R.drawable.ic_group_7)
                (listFragment[1] as? InboxFragment)?.let {
                    it.arrayList.forEachIndexed { index, inbox ->
                        if (it.arrayList[index].name.equals(home.name)) {
                            indexx = index
                        }
                    }
                    if (indexx != -1) {
                        mInboxDB?.getDao()?.deleteByName(it.arrayList[indexx].name)
                    }
                    launch {
                        mInboxDB?.getDao()?.insert(
                            Inbox(
                                avatar = home.avatar,
                                name = home.name,
                                message = " xin chào",
                                notification = "1",
                                dateTime = sdf2.format(date)
                            )
                        )
                        it.arrayList.clear()
                        var list = mInboxDB?.getDao()?.getAllInbox() as ArrayList
                        list.forEachIndexed { index, inbox ->
                            it.arrayList.add(list[index])
                        }
                        it.adapterInbox?.setList(it.arrayList)
                    }
                }
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main

    override fun onDestroy() {
        super.onDestroy()

        mJob.cancel()
    }

}

