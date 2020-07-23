package com.example.appsignin.Database

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.appsignin.Object.Inbox

@Dao
interface UserDao {
    @Query("SELECT * FROM User where email = :email and password= :password")
    fun getUser(
        email: String?,
        password: String?
    ): User?

    @Query("SELECT * FROM User WHERE email = :email")
    fun findUserByEmail(email: String): User?

    @Query("SELECT * FROM User WHERE password = :password")
    fun findUserByPass(password: String): User?

    @Insert
    suspend fun insert(user: User)
//
//    @Insert
//    fun insert(inbox: Inbox)
//
//    @Query("SELECT * FROM Inbox")
//    fun getAllInbox(
//    ): ArrayList<Inbox>

}