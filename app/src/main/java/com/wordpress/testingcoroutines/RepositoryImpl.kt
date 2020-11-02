package com.wordpress.testingcoroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(): Repository {
    override suspend fun someLongRunningOperation() = withContext(Dispatchers.IO) {
        delay(2000)
    }
}