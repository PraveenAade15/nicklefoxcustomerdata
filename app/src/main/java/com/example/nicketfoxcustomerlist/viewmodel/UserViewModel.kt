package com.example.nicketfoxcustomerlist.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nicketfoxcustomerlist.model.User
import com.example.nicketfoxcustomerlist.repository.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repo: UserRepo): ViewModel() {


    fun addUser(user: User){
        repo.addUserToRoom(user)
    }

    fun updateUser(user: User){
        repo.updateUser(user)
    }

    fun deleteUser(user: User){
        repo.deleteUser(user)
    }

    fun getAllMoney(): LiveData<List<User>>{
        return repo.getAllUser()
    }

}