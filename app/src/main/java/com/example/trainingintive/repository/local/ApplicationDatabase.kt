package com.example.trainingintive.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trainingintive.model.DogImageUrl

@Database(entities = arrayOf(DogImageUrl::class), version = 1, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun dogImageUrlDao(): DogImageDao
}
