package com.example.appsignin.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

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
    fun insert(user: User)

}