package com.wordpress.testingcoroutines

import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getListOfCountriesFromTheWeb(): Flow<List<Country>>
    fun getListOfCountriesFromTheDB(): Flow<List<Country>>
}