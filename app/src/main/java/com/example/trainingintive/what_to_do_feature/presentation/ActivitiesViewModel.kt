package com.example.trainingintive.what_to_do_feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trainingintive.repository.SchedulersProvider
import com.example.trainingintive.what_to_do_feature.data.repository.ActivityRepository
import com.example.trainingintive.what_to_do_feature.domain.model.ActivityModel
import javax.inject.Inject

class ActivitiesViewModel @Inject constructor(
    private val activityRepository: ActivityRepository,
    private val schedulers: SchedulersProvider
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
