package com.example.subnetter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.subnetter.ui.BottomNavigationBar

import com.example.subnetter.ui.theme.SubnetterTheme

class MainActivity : ComponentActivity() {
    /**
     * Called when the activity is starting.
     *
     * This method sets up the UI content of the activity. It enables edge-to-edge drawing, sets the UI theme to SubnetterTheme, and displays the bottom navigation bar.
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SubnetterTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    BottomNavigationBar()
                }
            }
        }
    }
}
