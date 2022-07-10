package com.example.nicketfoxcustomerlist.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nicketfoxcustomerlist.model.User

@Dao
interface UserDao {
    /**
     * CREATE
     */

    //insert data to room database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    /**
     * UPDATE
     */

    //update user details

    @Update
    fun update(user: User)
    /**
     * DELETE
     */

    //delete single user details

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM users_table")
    fun getAllUser() : LiveData<List<User>>

//    @Query("SELECT * FROM users_table ORDER BY ASC")

}