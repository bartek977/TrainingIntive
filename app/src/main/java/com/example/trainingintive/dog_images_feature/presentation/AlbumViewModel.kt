package com.example.trainingintive.dog_images_feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import com.example.trainingintive.dog_images_feature.domain.usecase.ChangeImagePositionsUseCase
import com.example.trainingintive.dog_images_feature.domain.usecase.GetAllUrlsUseCase
import com.example.trainingintive.dog_images_feature.domain.usecase.RemoveDogImageUseCase
import com.example.trainingintive.navigators.MainNavigator
import com.example.trainingintive.rx.SchedulersProvider
import com.example.trainingintive.util.MainScreenEvent
import com.example.trainingintive.util.plusAssign
import com.example.trainingintive.util.toErrorTextId
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class AlbumViewModel @Inject constructor(
    private val getAllUrlsUseCase: GetAllUrlsUseCase,
    private val removeDogImageUseCase: RemoveDogImageUseCase,
    private val changeImagePositionsUseCase: ChangeImagePositionsUseCase,
    private val schedulers: SchedulersProvider,
    private val navigator: MainNavigator
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _imageUrls = MutableLiveData<List<DogImageUrl>>(emptyList())
    val imageUrls: LiveData<List<DogImageUrl>> = _imageUrls

    init {
        getAllImages()
    }

    private fun getAllImages() {
        disposables +=
            getAllUrlsUseCase.execute()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe(
                    { _imageUrls.value = it.sortedBy { it.position } },
                    {
                        navigator.sendEvent(
                            MainScreenEvent.Error(it.toErrorTextId())
                        )
                    }
                )
    }

    fun removeImage(index: Int) {
        val imageUrl = _imageUrls.value!![index]
        disposables +=
            removeDogImageUseCase.execute(imageUrl)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe()
    }

    fun changeImagePositionAndUpdateDb(from: Int, to: Int) {
        disposables +=
            changeImagePositionsUseCase.execute(_imageUrls.value!!, from, to)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe()
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}
