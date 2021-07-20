package com.example.trainingintive.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.trainingintive.model.ActivityModel
import com.example.trainingintive.repository.Repository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ActivitiesViewModel(private val repository: Repository) : ViewModel() {

    init {
        repository.getDogImageUrl()
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.io())
            .subscribe(
                { _dog.postValue(it.message) },
                { _dog.postValue("błąd") }
            )
    }
    private val _dog = MutableLiveData<String>()
    val dog: LiveData<String> = _dog

    val _activities = MutableLiveData<List<ActivityModel>>(emptyList())
    val activities: LiveData<List<ActivityModel>> = _activities

    fun getActivity() {
        repository.getActivity()
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.io())
            .subscribe(
                { _activities.postValue(_activities.value!! + it) },
                { }
            )
    }
}

class ActivitiesViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ActivitiesViewModel::class.java)) {
            return ActivitiesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
