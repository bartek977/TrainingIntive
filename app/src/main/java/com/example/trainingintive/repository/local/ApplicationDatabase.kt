package com.example.trainingintive.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trainingintive.dog_images_feature.data.local.DogImageDao
import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl

@Database(entities = arrayOf(DogImageUrl::class), version = 1, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun dogImageUrlDao(): DogImageDao
}
