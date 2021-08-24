package com.example.trainingintive.dog_images_feature.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.trainingintive.dog_images_feature.data.entity.DogImageUrlDb
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface DogImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(imageUrl: DogImageUrlDb): Completable

    @Query("SELECT * from dogImageUrlDb")
    fun getAllImageUrls(): Flowable<List<DogImageUrlDb>>

    @Update
    fun update(imageUrl: DogImageUrlDb): Completable

    @Delete
    fun remove(imageUrl: DogImageUrlDb): Completable

    @Query("SELECT COUNT() FROM DogImageUrlDb")
    fun count(): Single<Int>

    @Update
    fun update(imageUrls: List<DogImageUrlDb>): Completable
}
