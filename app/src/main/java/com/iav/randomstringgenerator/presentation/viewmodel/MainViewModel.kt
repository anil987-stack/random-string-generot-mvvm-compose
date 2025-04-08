package com.iav.randomstringgenerator.presentation.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iav.randomstringgenerator.data.local.RandomStringEntity
import com.iav.randomstringgenerator.domain.usecase.GetRandomStringUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val useCase: GetRandomStringUseCase) : ViewModel() {
    val strings: StateFlow<List<RandomStringEntity>> get() = useCase.repository.stringList

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchRandomString(length: Int) {
        viewModelScope.launch {
            useCase.getString(length)
        }
    }

    fun deleteString(item: RandomStringEntity) {
        useCase.delete(item)
    }

    fun deleteAllStrings() {
        useCase.clear()
    }
}
