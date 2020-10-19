package com.wordpress.testingcoroutines

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
class ViewModelToUse @ViewModelInject constructor(private val repository: Repository) : ViewModel() {
    private var _state = MutableStateFlow(StateOfScreen.LOADING)
    val state: StateFlow<StateOfScreen> = _state
    fun start() {
        viewModelScope.launch {
            repository.someLongRunningOperation()
            _state.value = StateOfScreen.SUCCESS
        }
    }
}