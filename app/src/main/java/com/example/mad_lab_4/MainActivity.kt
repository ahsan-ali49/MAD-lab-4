package com.example.mad_lab_4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.mad_lab_4.ui.theme.MADlab4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MADlab4Theme {
                ShowPreferencesDataScreen()
            }
        }
        val sharedPreferences : SharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        
        val editor = sharedPreferences.edit()
        editor.putString("color", "No Color")
        editor.apply()
    }
}

@Composable
fun ShowPreferencesDataScreen() {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "MAD Lab 4")
            Spacer(modifier = Modifier.size(50.dp))
            Button(onClick = {
                val intent = Intent(context, Task1::class.java)
                context.startActivity(intent)
            }) {
                Text(text = "Task 1")
            }
            Spacer(modifier = Modifier.size(20.dp))
            Button(onClick = {
                val intent = Intent(context, Task2::class.java)
                context.startActivity(intent)
            }) {
                Text(text = "Task 2")
            }
            Spacer(modifier = Modifier.size(20.dp))
            Button(onClick = {
                val intent = Intent(context, Task3::class.java)
                context.startActivity(intent)
            }) {
                Text(text = "Task 3")
            }
            Spacer(modifier = Modifier.size(20.dp))
            Button(onClick = {
                val intent = Intent(context, Task4::class.java)
                context.startActivity(intent)
            }) {
                Text(text = "Task 4")
            }
            Spacer(modifier = Modifier.size(20.dp))
            Button(onClick = {
                val intent = Intent(context, Task5::class.java)
                context.startActivity(intent)
            }) {
                Text(text = "Task 5")
            }
        }
    }
}