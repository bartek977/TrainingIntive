package com.example.trainingintive.what_to_do_feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trainingintive.navigators.MainNavigator
import com.example.trainingintive.rx.SchedulersProvider
import com.example.trainingintive.util.ErrorMessageId
import com.example.trainingintive.util.MainScreenEvent
import com.example.trainingintive.what_to_do_feature.domain.model.ActivityModel
import com.example.trainingintive.what_to_do_feature.domain.usecase.GetActivityUseCase
import java.lang.Exception
import javax.inject.Inject

class ActivitiesViewModel @Inject constructor(
    private val getActivityUseCase: GetActivityUseCase,
    private val schedulers: SchedulersProvider,
    private val navigator: MainNavigator
) : ViewModel() {

    private val _activities = MutableLiveData<List<ActivityModel>>(emptyList())
    val activities: LiveData<List<ActivityModel>> = _activities

    fun getActivity() {
        getActivityUseCase.execute()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe(
                { _activities.value = _activities.value!! + it },
                {
                    val errorMessageId = ErrorMessageId.getId(it as Exception)
                    navigator.sendEvent(
                        MainScreenEvent.Error(errorMessageId)
                    )
                }
            )
    }
}
