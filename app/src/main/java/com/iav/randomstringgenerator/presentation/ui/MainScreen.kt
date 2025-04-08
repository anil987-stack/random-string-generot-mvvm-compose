package com.iav.randomstringgenerator.presentation.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iav.randomstringgenerator.data.local.RandomStringEntity
import com.iav.randomstringgenerator.presentation.viewmodel.MainViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val strings by viewModel.strings.collectAsState()
    var inputLength by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()) {

        OutlinedTextField(
            value = inputLength,
            onValueChange = { inputLength = it },
            label = { Text("Enter length") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = {
                viewModel.fetchRandomString(inputLength.toIntOrNull() ?: 0)
            }) {
                Text("Generate")
            }
            Button(onClick = {
                viewModel.deleteAllStrings()
            }) {
                Text("Clear All")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(strings) { item ->
                StringItem(item, onDelete = { viewModel.deleteString(it) })
            }
        }
    }
}

@Composable
fun StringItem(item: RandomStringEntity, onDelete: (RandomStringEntity) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Text("Value: ${item.value}")
        Text("Length: ${item.length}")
        Text("Created: ${item.created}")
        Button(onClick = { onDelete(item) }) {
            Text("Delete")
        }
    }
}
