package com.example.nicketfoxcustomerlist.di

import android.app.Application
import android.content.Context
import android.service.autofill.UserData
import androidx.room.Room
import com.example.nicketfoxcustomerlist.local.room.UserDao
import com.example.nicketfoxcustomerlist.local.room.UserDatabase
import com.example.nicketfoxcustomerlist.repository.UserRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getDAOObject(@ApplicationContext context: Context): UserDao {
        return Room.databaseBuilder(context, UserDatabase::class.java,"user_DB")
            .build().getUserDao()
    }

    @Singleton
    @Provides
    fun getRepo(userDao: UserDao): UserRepo {
        return UserRepo(userDao)
    }
}