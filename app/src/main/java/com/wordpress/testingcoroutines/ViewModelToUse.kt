package com.wordpress.testingcoroutines

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*


@ExperimentalCoroutinesApi
class ViewModelToUse @ViewModelInject constructor(private val repository: Repository,
                                                  @IODispatcher private val ioDispatcher: CoroutineDispatcher) : ViewModel() {
    private var _state = MutableStateFlow(StateOfScreen.LOADING)
    var countries: List<Country>? = null
    val state: StateFlow<StateOfScreen> = _state
    fun start() {
        repository.getListOfCountriesFromTheWeb()
            .catch {
                emitAll(repository.getListOfCountriesFromTheDB())
            }.onEach {
                if (it.isEmpty()) {
                    _state.value = StateOfScreen.ERROR
                } else {
                    countries = it
                    _state.value = StateOfScreen.SUCCESS
                }
            }.flowOn(ioDispatcher).launchIn(viewModelScope)
    }
}