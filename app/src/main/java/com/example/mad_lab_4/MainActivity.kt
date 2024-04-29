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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mad_lab_4.ui.theme.MADlab4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MADlab4Theme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "ShowPreferencesDataScreen"){
                    composable("ShowPreferencesDataScreen"){
                        ShowPreferencesDataScreen()
                    }
                    composable(
                        "Task1/{color}",
                        listOf(navArgument("color"){ type = NavType.StringType })
                    ){
                        backStackEntry ->
                        val color = backStackEntry.arguments?.getString("color") ?: ""
                        Task1Screen(color)
                    }
                }
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
            Button(onClick = {
                val intent = Intent(context, Task1::class.java)
                context.startActivity(intent)
            }) {
                Text(text = "Send Color")
            }
        }
    }
}

@Composable
fun Task1Screen(color: String){
    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = color)
        }
    }
}