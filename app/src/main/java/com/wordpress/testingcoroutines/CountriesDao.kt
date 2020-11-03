package com.wordpress.testingcoroutines

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CountriesDao {
    @Insert
    fun insertCountries(countries: List<Country>)

    @Query("SELECT * FROM country")
    fun getCountries(): Flow<List<Country>>
}