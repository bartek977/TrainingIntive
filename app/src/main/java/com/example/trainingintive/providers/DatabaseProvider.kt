package com.example.trainingintive.providers

import android.content.Context
import androidx.room.Room
import com.example.trainingintive.data.ApplicationDatabase

private const val DATABASE_NAME = "application_database"

object DatabaseProvider {
    fun get(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            ApplicationDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
}
