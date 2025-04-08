package com.iav.randomstringgenerator.domain.repository

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.iav.randomstringgenerator.data.local.RandomStringEntity
import com.iav.randomstringgenerator.utils.getRandomStringFromProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RandomStringRepository(private val context: Context) {
    private val _stringList = MutableStateFlow<List<RandomStringEntity>>(emptyList())
    val stringList: StateFlow<List<RandomStringEntity>> = _stringList

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchNewString(length: Int) {
        val result = context.getRandomStringFromProvider(length)
        if (result != null) {
            val newItem = RandomStringEntity(
                value = result.value,
                length = result.length,
                created = result.created
            )
            _stringList.update { it + newItem }
        } else {
            Toast.makeText(context, "Content provider not available", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteString(item: RandomStringEntity) {
        _stringList.update { list -> list.filterNot { it.id == item.id && it.value == item.value } }
    }

    fun clearAll() {
        _stringList.value = emptyList()
    }
}
