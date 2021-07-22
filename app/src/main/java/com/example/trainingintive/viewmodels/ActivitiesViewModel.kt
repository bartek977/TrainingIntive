package com.example.trainingintive.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trainingintive.model.ActivityModel
import kotlin.random.Random

class ActivitiesViewModel : ViewModel() {
    val activities = MutableLiveData<List<ActivityModel>>(emptyList())

    val tempActivities = listOf(
        ActivityModel("play football", "", 2),
        ActivityModel("go for a walk", "", 2),
        ActivityModel("do nothing", "", 2)
    )

    fun getActivity() {
        val randomIndex = Random.nextInt(0, 3)
        activities.value = activities.value?.plus(tempActivities[randomIndex])
    }
}
