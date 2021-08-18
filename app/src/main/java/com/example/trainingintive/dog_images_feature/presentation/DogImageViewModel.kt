package com.example.trainingintive.dog_images_feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trainingintive.dog_images_feature.data.repository.DogImageRepository
import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import com.example.trainingintive.navigators.MainNavigator
import com.example.trainingintive.rx.SchedulersProvider
import com.example.trainingintive.util.MainScreenEvent
import com.example.trainingintive.util.plusAssign
import com.example.trainingintive.util.toErrorTextId
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.lang.Exception
import javax.inject.Inject

class DogImageViewModel @Inject constructor(
    private val repository: DogImageRepository,
    private val schedulers: SchedulersProvider,
    private val mainNavigator: MainNavigator
) : ViewModel() {

    private val _dog = MutableLiveData<String>()
    val dog: LiveData<String> = _dog

    private val disposables = CompositeDisposable()

    init {
        disposables +=
            repository.getDogImageUrl()
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
            repository.insertIntoLocalDatabase(dogImageUrl)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}
