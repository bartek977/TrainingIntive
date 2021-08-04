package com.example.trainingintive.navigators

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.trainingintive.util.Event

abstract class Navigator {

    protected var activity: AppCompatActivity? = null
    private val event = MutableLiveData<Event>(Event.Nothing)

    fun attachActivity(activity: AppCompatActivity) {
        this.activity = activity
        event.observe(
            activity,
            {
                action(it)
            }
        )
    }

    fun detachActivityAndResetEvent() {
        activity = null
        resetEvent()
    }

    fun sendEvent(event: Event) {
        this.event.value = event
    }

    protected fun finishActivity() {
        activity?.finish()
    }

    private fun resetEvent() {
        event.value = Event.Nothing
    }

    abstract fun action(event: Event)
}
