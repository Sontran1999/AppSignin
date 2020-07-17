package com.example.appsignin.Array

import com.example.appsignin.Object.Home
import com.example.appsignin.Object.Inbox

interface List {
    var listInbox: ArrayList<Home>
    fun get(): ArrayList<Home> {
        return listInbox
    }
    fun set( value:ArrayList<Home>){
        listInbox = value
    }

}