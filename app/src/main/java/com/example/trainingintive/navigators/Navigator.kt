package com.example.trainingintive.navigators

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.trainingintive.util.Event

abstract class Navigator {

    protected var activity: AppCompatActivity? = null
    private val event = MutableLiveData<Event>()

    fun attachActivity(activity: AppCompatActivity) {
        this.activity = activity
        event.observe(
            activity,
            {
                action(it)
            }
        )
    }

    fun detachActivity() {
        activity = null
    }

    fun sendEvent(event: Event) {
        this.event.postValue(event)
    }

    abstract fun action(event: Event)
}
