package com.example.trainingintive.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.trainingintive.model.DogImageUrl
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface DogImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dogImageUrl: DogImageUrl): Completable

    @Query("SELECT * from dogImageUrl")
    fun getAllImageUrls(): Flowable<List<DogImageUrl>>
}
