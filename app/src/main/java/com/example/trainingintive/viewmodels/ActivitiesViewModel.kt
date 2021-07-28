package com.example.trainingintive.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trainingintive.model.ActivityModel
import com.example.trainingintive.repository.ActivityRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ActivitiesViewModel @Inject constructor(private val activityRepository: ActivityRepository) : ViewModel() {
    private val _activities = MutableLiveData<List<ActivityModel>>(emptyList())
    val activities: LiveData<List<ActivityModel>> = _activities

    fun getActivity() {
        activityRepository.getActivity()
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.io())
            .subscribe(
                { _activities.postValue(_activities.value!! + it) },
                { }
            )
    }
}
