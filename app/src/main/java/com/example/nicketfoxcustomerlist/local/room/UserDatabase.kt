package com.example.nicketfoxcustomerlist.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nicketfoxcustomerlist.model.User

@Database(entities = [User::class],version = 2)
abstract class UserDatabase: RoomDatabase() {

    abstract fun getUserDao() : UserDao

}