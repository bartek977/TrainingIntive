package com.example.trainingintive.ui

import androidx.lifecycle.ViewModel
import com.example.trainingintive.model.ActivityModel

class ActivitiesViewModel : ViewModel() {
    val activities = listOf(
        ActivityModel("play football", "", 2),
        ActivityModel("go for a walk", "", 2),
        ActivityModel("do nothing", "", 2),
    )
}
