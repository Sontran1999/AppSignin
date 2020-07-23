package com.example.appsignin.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserRoomDatabase: RoomDatabase() {
    abstract fun getDao():UserDao

    companion object {
        private var INSTANCE: UserRoomDatabase?= null
        private const val DB_NAME = "user_database"

        fun getDatabase(context: Context): UserRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserRoomDatabase::class.java,
                    DB_NAME
                ).run { allowMainThreadQueries() }.build()
                INSTANCE = instance
                return instance
            }
        }
    }

}