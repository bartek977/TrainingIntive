package com.example.trainingintive

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

abstract class RulesForTests {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
}
