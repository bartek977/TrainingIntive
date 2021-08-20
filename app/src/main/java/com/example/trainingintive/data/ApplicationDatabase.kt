package com.example.trainingintive.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trainingintive.dog_images_feature.data.entity.DogImageUrlDb
import com.example.trainingintive.dog_images_feature.data.local.DogImageDao

@Database(entities = [DogImageUrlDb::class], version = 6, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun dogImageUrlDao(): DogImageDao
}
