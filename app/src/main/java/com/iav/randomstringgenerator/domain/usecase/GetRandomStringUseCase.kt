package com.iav.randomstringgenerator.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.iav.randomstringgenerator.data.local.RandomStringEntity
import com.iav.randomstringgenerator.domain.repository.RandomStringRepository

class GetRandomStringUseCase(val repository: RandomStringRepository) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getString(length: Int) = repository.fetchNewString(length)
    fun delete(item: RandomStringEntity) = repository.deleteString(item)
    fun clear() = repository.clearAll()
}
