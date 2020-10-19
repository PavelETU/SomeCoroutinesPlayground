package com.wordpress.testingcoroutines

import kotlinx.coroutines.delay
import javax.inject.Inject

class RepositoryImpl @Inject constructor(): Repository {
    override suspend fun someLongRunningOperation() {
        delay(1000)
    }
}