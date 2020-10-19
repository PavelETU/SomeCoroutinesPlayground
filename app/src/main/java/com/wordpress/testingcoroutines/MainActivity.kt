package com.wordpress.testingcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlin.coroutines.coroutineContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: ViewModelToUse by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}