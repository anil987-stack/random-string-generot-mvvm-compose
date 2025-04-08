package com.iav.randomstringgenerator

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.iav.randomstringgenerator.domain.repository.RandomStringRepository
import com.iav.randomstringgenerator.domain.usecase.GetRandomStringUseCase
import com.iav.randomstringgenerator.presentation.ui.MainScreen
import com.iav.randomstringgenerator.presentation.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = RandomStringRepository(applicationContext)
        val useCase = GetRandomStringUseCase(repository)
        val viewModel = MainViewModel(useCase)

        setContent {
            MainScreen(viewModel)
        }
    }
}
