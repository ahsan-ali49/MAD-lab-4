package com.example.mad_lab_4

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.mad_lab_4.ui.theme.MADlab4Theme
import kotlinx.coroutines.launch

val Context.dataStore by preferencesDataStore(name = "counter_prefs")
class Task2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MADlab4Theme {
                val context = LocalContext.current
                CounterScreen(context.dataStore)
            }
        }
    }
}

@Composable
fun CounterScreen(dataStore: DataStore<Preferences>) {
    val coroutineScope = rememberCoroutineScope()
    val counter = remember { mutableStateOf(0) }
    val preferencesKey = intPreferencesKey("counter")

    LaunchedEffect(key1 = Unit) {
        dataStore.data.collect { preferences ->
            counter.value = preferences[preferencesKey] ?: 0
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Counter: ${counter.value}")
        Button(onClick = {
            coroutineScope.launch {
                updateCounterInDataStore(dataStore, counter.value + 1)
            }
        }) {
            Text(text = "Increment")
        }
    }
}

suspend fun updateCounterInDataStore(dataStore: DataStore<Preferences>, counter: Int) {
    val preferencesKey = intPreferencesKey("counter")
    dataStore.edit { preferences ->
        preferences[preferencesKey] = counter
    }
}
