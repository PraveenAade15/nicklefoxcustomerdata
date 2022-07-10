package com.example.nicketfoxcustomerlist.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nicketfoxcustomerlist.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM users_table")
    fun getAllUser() : LiveData<List<User>>



}