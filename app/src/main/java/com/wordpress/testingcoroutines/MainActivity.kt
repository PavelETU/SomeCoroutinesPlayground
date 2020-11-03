package com.wordpress.testingcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.coroutineScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: ViewModelToUse by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val contextOfTheScreen = findViewById<TextView>(R.id.context_of_the_screen)
        val loadingIndicator = findViewById<ProgressBar>(R.id.loading_indicator)
        viewModel.start()
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.state.collect {
                when(it) {
                    StateOfScreen.SUCCESS -> {
                        contextOfTheScreen.visibility = View.VISIBLE
                        loadingIndicator.visibility = View.INVISIBLE
                        contextOfTheScreen.text = "There are ${viewModel.countries!!.size} countries fetched"
                    }
                    StateOfScreen.LOADING -> {
                        contextOfTheScreen.visibility = View.INVISIBLE
                        loadingIndicator.visibility = View.VISIBLE
                    }
                    StateOfScreen.ERROR -> {
                        contextOfTheScreen.visibility = View.VISIBLE
                        loadingIndicator.visibility = View.INVISIBLE
                        contextOfTheScreen.text = getString(R.string.error)
                    }
                }
            }
        }
    }
}