package com.wordpress.testingcoroutines

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val lookupService: LookupService, private val db: RoomDB): Repository {
    override fun getListOfCountriesFromTheWeb(): Flow<List<Country>> {
        return flow {
            val countries = lookupService.getCountries()
            db.countriesDao().insertCountries(countries)
            emit(countries)
        }
    }

    override fun getListOfCountriesFromTheDB() = db.countriesDao().getCountries()
}