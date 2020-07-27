package com.example.appsignin.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.appsignin.Object.Inbox

@Database(entities = [User::class, Inbox::class], version = 2)
abstract class UserRoomDatabase: RoomDatabase() {
    abstract fun getDao():UserDao

    companion object {
        private var INSTANCE: UserRoomDatabase?= null
        private const val DB_NAME = "user_database"

//        val MIGRATION_1_2 = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL(
//                    "CREATE TABLE Inbox (id INTEGER NOT NULL, avatar INTEGER NOT NULL, name TEXT NOT NULL, message TEXT NOT NULL, notification TEXT NOT NULL, date TEXT NOT NULL, Primary Key(id))"
//                )
//            }
//        }

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
                ).fallbackToDestructiveMigration().run { allowMainThreadQueries() }.build()
                INSTANCE = instance
                return instance
            }
        }
    }

}