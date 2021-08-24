package com.example.trainingintive.dog_images_feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import com.example.trainingintive.dog_images_feature.domain.usecase.GetDogImageUrlUseCase
import com.example.trainingintive.dog_images_feature.domain.usecase.InsertDogImageUrlUseCase
import com.example.trainingintive.navigators.MainNavigator
import com.example.trainingintive.rx.SchedulersProvider
import com.example.trainingintive.util.MainScreenEvent
import com.example.trainingintive.util.plusAssign
import com.example.trainingintive.util.toErrorTextId
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class DogImageViewModel @Inject constructor(
    private val getDogImageUrlUseCase: GetDogImageUrlUseCase,
    private val insertDogImageUrlUseCase: InsertDogImageUrlUseCase,
    private val schedulers: SchedulersProvider,
    private val mainNavigator: MainNavigator
) : ViewModel() {

    private val _dog = MutableLiveData<String>()
    val dog: LiveData<String> = _dog

    private val disposables = CompositeDisposable()

    init {
        disposables +=
            getDogImageUrlUseCase.execute()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe(
                    { dog ->
                        _dog.value = dog.url
                        insertDogImageIntoLocalDatabase(dog)
                    },
                    {
                        mainNavigator.sendEvent(
                            MainScreenEvent.Error(it.toErrorTextId())
                        )
                    }
                )
    }

    private fun insertDogImageIntoLocalDatabase(dogImageUrl: DogImageUrl) {
        disposables +=
            insertDogImageUrlUseCase.execute(dogImageUrl)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}
