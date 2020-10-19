package com.wordpress.testingcoroutines

interface Repository {
    suspend fun someLongRunningOperation()
}