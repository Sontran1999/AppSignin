package com.example.appsignin.Object

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.chrono.ChronoLocalDateTime

@Entity(tableName = "Inbox")
class Inbox(
    @ColumnInfo(name = "avatar")
    var avatar: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "message")
    var message: String,
    @ColumnInfo(name = "notification")
    var notification: String,
    @ColumnInfo(name = "dateTime")
    var dateTime: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}