package com.example.trainingintive.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trainingintive.dog_images_feature.data.local.DogImageDao
import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl

// TODO Look at Android Studio warnings, here "'arrayOf' call should be replaced with array literal [...]"
@Database(entities = arrayOf(DogImageUrl::class), version = 2, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun dogImageUrlDao(): DogImageDao
}
