package com.example.mad_lab_4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.mad_lab_4.ui.theme.MADlab4Theme
import kotlin.math.log

class Task1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MADlab4Theme {
                Task1Screen()
            }
        }
    }
}

@Composable
fun Task1Screen(){
    val userColor = remember {
        mutableStateOf("")
    }
    val retrievedColor = remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val sharedPreferences : SharedPreferences = context.getSharedPreferences("MyColor", Context.MODE_PRIVATE)

    val editor = sharedPreferences.edit()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TextField(
                value = userColor.value,
                onValueChange = { userColor.value = it },
                label = {Text(text = "Enter Color")}
            )
            Button(onClick = {
                editor.putString("colorName", userColor.value)
                editor.apply()
            }) {
                Text(text = "Save")
            }
            Text(text = "Retreived Color: ${retrievedColor.value}")
            Button(onClick = {
                val sharedPreferencesForValue = context.getSharedPreferences("MyColor", Context.MODE_PRIVATE)
                val data = sharedPreferencesForValue.getString("colorName", "Default Value")
                if (data != null) {
                    retrievedColor.value = data
                }
            }) {
                Text(text = "Retreive")
            }
        }
    }
}