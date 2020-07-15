package com.example.appsignin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
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
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), View.OnClickListener {

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
                view()
            }
            R.id.btn_inbox ->{
                view2()
            }
        }
    }
    fun view(){
        var homeFragment: HomeFragment = HomeFragment()
        var fragmentManager: FragmentManager = supportFragmentManager
        var fragmentTransaction: FragmentTransaction =
            fragmentManager.beginTransaction().replace(R.id.frameContent, homeFragment)
        fragmentTransaction.commit()
    }

    fun view2(){
        var inboxFragment: InboxFragment = InboxFragment()
        var fragmentManager: FragmentManager = supportFragmentManager
        var fragmentTransaction: FragmentTransaction =
            fragmentManager.beginTransaction().replace(R.id.frameContent, inboxFragment).addToBackStack("")
        fragmentTransaction.commit()
    }


}

