package com.wordpress.testingcoroutines

import retrofit2.http.GET

interface LookupService {
    @GET("/countries")
    suspend fun getCountries(): List<Country>
}