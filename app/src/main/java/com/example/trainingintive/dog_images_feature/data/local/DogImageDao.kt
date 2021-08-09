package com.example.trainingintive.dog_images_feature.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface DogImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dogImageUrl: DogImageUrl): Completable

    @Query("SELECT * from dogImageUrl")
    fun getAllImageUrls(): Flowable<List<DogImageUrl>>
}
