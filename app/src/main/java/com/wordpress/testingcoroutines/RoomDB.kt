package com.wordpress.testingcoroutines

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Country::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun countriesDao(): CountriesDao
}