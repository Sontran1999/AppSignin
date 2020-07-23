package com.example.appsignin.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appsignin.Adapter.HomeAdapter
import com.example.appsignin.HomeActivity
import com.example.appsignin.Interface.OnItemClick
import com.example.appsignin.Object.Home
import com.example.appsignin.R


class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: View = inflater.inflate(R.layout.fragment_home, container, false)

        viewSpiner(view)
        viewRecycler(view)
        return view
    }

    fun viewSpiner(view: View) {
        var spinner: Spinner = view.findViewById(R.id.spn_category) as Spinner
        var list: ArrayList<String> = ArrayList()
        list.add("All Categories")
        list.add("Sport")
        val adapter =
            ArrayAdapter(view.context, R.layout.support_simple_spinner_dropdown_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice)
        spinner.setAdapter(adapter)
    }

    fun viewRecycler(view: View) {
        var recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        var arrayList: ArrayList<Home> = ArrayList()

        arrayList.add(
            Home(
                R.drawable.avatar1, "Martin Palmer", "Today, 03:24 PM",
                "What is the loop of Creation? How is there something from nothing? In spite of the fact that it is impossible to prove that anythin….",
                R.drawable.rectangle_copy, "$340.00"
            )
        )
        arrayList.add(
            Home(
                R.drawable.avatar2, "Pearl Myers", "Yesterday",
                "I am looking for a chilled out roommate for a house on 17th floor of a XYZ appartment.",
                0,/* R.drawable.ic_001_heart, R.drawable.ic_003_comment_1,*/"$290.00"
            )
        )
        arrayList.add(
            Home(
                R.drawable.avatar3, "Gary Rose", "Yesterday",
                "I am looking for a chilled out roommate for a house on 17th floor of a XYZ appartment.",
                0, /*R.drawable.ic_001_heart, R.drawable.ic_003_comment_1,*/"$290.00"
            )
        )
        arrayList.add(
            Home(
                R.drawable.avatar4, "Peter", "Today, 03:24 PM",
                "What is the loop of Creation? How is there something from nothing? In spite of the fact that it is impossible to prove that anythin….",
                R.drawable.bien, "$340.00"
            )
        )

        var adapterHome: HomeAdapter =
            HomeAdapter(view.context, arrayList, activity as HomeActivity)
        recyclerView.adapter = adapterHome

    }
}



