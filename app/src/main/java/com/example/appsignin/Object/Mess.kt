package com.example.appsignin.Object

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Inbox")
class Mess(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    @ColumnInfo(name = "avatar")
    var avatar:Int,
    @ColumnInfo(name = "name")
    var name:String,
    @ColumnInfo(name = "message")
    var message:String,
    @ColumnInfo(name = "notification")
    var notification: String,
    @ColumnInfo(name = "dateTime")
    var dateTime: String)