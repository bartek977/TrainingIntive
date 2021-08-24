package com.example.trainingintive.dog_images_feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import com.example.trainingintive.dog_images_feature.domain.usecase.GetAllUrlsUseCase
import com.example.trainingintive.dog_images_feature.domain.usecase.RemoveDogImageUseCase
import com.example.trainingintive.navigators.MainNavigator
import com.example.trainingintive.rx.SchedulersProvider
import com.example.trainingintive.util.MainScreenEvent
import com.example.trainingintive.util.toErrorTextId
import javax.inject.Inject

class AlbumViewModel @Inject constructor(
    private val getAllUrlsUseCase: GetAllUrlsUseCase,
    private val removeDogImageUseCase: RemoveDogImageUseCase,
    private val schedulers: SchedulersProvider,
    private val navigator: MainNavigator
) : ViewModel() {

    private val _imageUrls = MutableLiveData<List<DogImageUrl>>(emptyList())
    val imageUrls: LiveData<List<DogImageUrl>> = _imageUrls

    init {
        getAllImages()
    }

    private fun getAllImages() {
        getAllUrlsUseCase.execute()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe(
                { _imageUrls.value = it },
                {
                    navigator.sendEvent(
                        MainScreenEvent.Error(it.toErrorTextId())
                    )
                }
            )
    }

    fun changeImagePosition(from: Int, to: Int) {}

    fun removeImage(index: Int) {
        val imageUrl = _imageUrls.value!![index]
        removeDogImageUseCase.execute(imageUrl)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe()
    }
}
