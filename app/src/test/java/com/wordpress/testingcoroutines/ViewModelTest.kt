package com.wordpress.testingcoroutines

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
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
            override fun getListOfCountriesFromTheWeb(): Flow<List<Country>> {
                return flowOf(listOf(Country("Ireland", "Ireland", "Irl"))).onEach { delay(1000) }
            }

            override fun getListOfCountriesFromTheDB(): Flow<List<Country>> {
                return flowOf(listOf(Country("Ireland", "Ireland", "Irl")))
            }
        }, coroutineTestRule.testDispatcher)
    }

    @Test
    fun `test state`() = coroutineTestRule.testDispatcher.runBlockingTest {
        viewModel.start()

        assertEquals(StateOfScreen.LOADING, viewModel.state.value)

        advanceTimeBy(1000)

        assertEquals(StateOfScreen.SUCCESS, viewModel.state.value)
    }
}