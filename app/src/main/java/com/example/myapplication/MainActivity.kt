package com.example.myapplication
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.common.viewmodel.CandidateViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication : Application() {

}
    @AndroidEntryPoint
    class MainActivity : ComponentActivity() {


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val viewModel: CandidateViewModel by viewModels()
            setContent {
                MyApplicationTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val state by viewModel._state.collectAsState()
                        TabRowScreen(state = state, onEvent = viewModel::onEvent)
                    }
                }
            }
        }
    }
