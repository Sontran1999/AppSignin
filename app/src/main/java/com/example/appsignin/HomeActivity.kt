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
import com.example.appsignin.Fragment.HomeFragment
import com.example.appsignin.Fragment.InboxFragment
import com.example.appsignin.Object.Home
import com.example.appsignin.Object.Inbox
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity() : AppCompatActivity(), View.OnClickListener{
    var listHome:ArrayList<Home>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var ac: ActionBar? = supportActionBar
        if (ac != null) {
            ac.hide()
        }

        btn_home.setOnClickListener(this)
        btn_inbox.setOnClickListener(this)
        view()


    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_home ->{
                var homeFragment: HomeFragment = HomeFragment()
                if(homeFragment.isAdded){
                    var fragmentManager: FragmentManager = supportFragmentManager
                    var fragmentTransaction: FragmentTransaction =
                        fragmentManager.beginTransaction()
                    fragmentTransaction.show(homeFragment)
//                    supportFragmentManager.popBackStackImmediate("fragHome", 0)
                }
                else{
                    supportFragmentManager.popBackStackImmediate("fragHome", 0)
                }


            }
            R.id.btn_inbox ->{
                var inboxFragment: InboxFragment = InboxFragment(listHome)
                if(inboxFragment.isAdded) {
                    var fragmentManager: FragmentManager = supportFragmentManager
                    var fragmentTransaction: FragmentTransaction =
                        fragmentManager.beginTransaction()
                    fragmentTransaction.show(inboxFragment)
                }
                else {
                    view2()
                }
            }
        }
    }
    fun view(){
        var homeFragment: HomeFragment = HomeFragment()
        var fragmentManager: FragmentManager = supportFragmentManager
        var fragmentTransaction: FragmentTransaction =
            fragmentManager.beginTransaction().replace(R.id.frameContent, homeFragment,"aaa").addToBackStack("fragHome")
        fragmentTransaction.commit()
    }

    fun view2(){
        var inboxFragment: InboxFragment = InboxFragment(listHome)
        var fragmentManager: FragmentManager = supportFragmentManager
        var fragmentTransaction: FragmentTransaction =
            fragmentManager.beginTransaction().replace(R.id.frameContent, inboxFragment,"bbb").addToBackStack("fragInbox")
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount>0) {
            supportFragmentManager.popBackStack()
        }
        else {
            super.onBackPressed()
        }
    }

    fun passDataToChild(listHome:ArrayList<Home>){

        this.listHome =listHome
    }

}

