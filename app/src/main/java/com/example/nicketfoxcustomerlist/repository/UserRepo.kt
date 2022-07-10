package com.example.nicketfoxcustomerlist.repository

import androidx.lifecycle.LiveData
import com.example.nicketfoxcustomerlist.local.room.UserDao
import com.example.nicketfoxcustomerlist.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserRepo @Inject constructor(private val userDao: UserDao) {

    //insert user details to room

    fun addUserToRoom(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.insert(user)
        }
    }
    //update user details to room

    fun updateUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.update(user)
        }
    }
    //delete single user record
    fun deleteUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.delete(user)
        }
    }
    // get all user details

    fun getAllUser(): LiveData<List<User>> {
        return userDao.getAllUser()
    }


}