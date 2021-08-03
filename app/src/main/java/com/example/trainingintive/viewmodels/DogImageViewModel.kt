package com.example.trainingintive.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trainingintive.model.DogImageUrl
import com.example.trainingintive.repository.DogImageRepository
import com.example.trainingintive.util.plusAssign
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DogImageViewModel @Inject constructor(
    private val repository: DogImageRepository
) : ViewModel() {

    private val _dog = MutableLiveData<String>()
    val dog: LiveData<String> = _dog

    private val disposables = CompositeDisposable()

    init {
        disposables +=
            repository.getDogImageUrl()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { dog ->
                        _dog.postValue(dog.url)
                        insertDogImageIntoLocalDatabase(dog)
                    },
                    { error -> _dog.postValue(error.localizedMessage) }
                )
    }

    private fun insertDogImageIntoLocalDatabase(dogImageUrl: DogImageUrl) {
        disposables +=
            repository.insertIntoLocalDatabase(dogImageUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}
