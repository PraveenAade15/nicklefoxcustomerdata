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

    fun addUserToRoom(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.insert(user)
        }
    }

    fun updateUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.update(user)
        }
    }

    fun deleteUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.delete(user)
        }
    }

    fun getAllUser(): LiveData<List<User>> {
        return userDao.getAllUser()
    }


}