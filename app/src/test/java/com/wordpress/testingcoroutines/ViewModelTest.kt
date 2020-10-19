package com.wordpress.testingcoroutines

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
class ViewModelTest {
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()
    private lateinit var viewModel: ViewModelToUse
    @Before
    fun setUp() {
        viewModel = ViewModelToUse(object : Repository {
            override suspend fun someLongRunningOperation() {
                delay(1000)
            }
        })
    }

    @Test
    fun view() = coroutineTestRule.testDispatcher.runBlockingTest {
        viewModel.start()

        assertEquals(StateOfScreen.LOADING, viewModel.state.value)

        advanceTimeBy(1000)

        assertEquals(StateOfScreen.SUCCESS, viewModel.state.value)
    }
}