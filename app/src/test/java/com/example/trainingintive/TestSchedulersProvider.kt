package com.example.trainingintive

import com.example.trainingintive.repository.SchedulersProvider
import io.reactivex.rxjava3.schedulers.Schedulers

class TestSchedulersProvider : SchedulersProvider() {

    override fun io() = Schedulers.trampoline()
    override fun ui() = Schedulers.trampoline()
}
