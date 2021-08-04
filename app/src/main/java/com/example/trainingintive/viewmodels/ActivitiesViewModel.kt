package com.example.trainingintive.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trainingintive.model.ActivityModel
import com.example.trainingintive.repository.ActivityRepository
import com.example.trainingintive.repository.Schedulers
import javax.inject.Inject

class ActivitiesViewModel @Inject constructor(
    private val activityRepository: ActivityRepository,
    private val schedulers: Schedulers
) : ViewModel() {

    private val _activities = MutableLiveData<List<ActivityModel>>(emptyList())
    val activities: LiveData<List<ActivityModel>> = _activities

    fun getActivity() {
        activityRepository.getActivity()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe(
                { _activities.postValue(_activities.value!! + it) },
                { }
            )
    }
}
