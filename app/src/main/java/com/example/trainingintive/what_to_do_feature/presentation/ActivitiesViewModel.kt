package com.example.trainingintive.what_to_do_feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trainingintive.rx.SchedulersProvider
import com.example.trainingintive.what_to_do_feature.domain.model.ActivityModel
import com.example.trainingintive.what_to_do_feature.domain.usecase.GetActivityUseCase
import javax.inject.Inject

class ActivitiesViewModel @Inject constructor(
    private val getActivityUseCase: GetActivityUseCase,
    private val schedulers: SchedulersProvider
) : ViewModel() {

    private val _activities = MutableLiveData<List<ActivityModel>>(emptyList())
    val activities: LiveData<List<ActivityModel>> = _activities

    fun getActivity() {
        getActivityUseCase.execute()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe(
                { _activities.postValue(_activities.value!! + it) },
                { }
            )
    }
}
