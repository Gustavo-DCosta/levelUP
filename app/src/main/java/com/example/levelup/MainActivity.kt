package com.example.levelup

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.levelup.ui.theme.LevelUPTheme

class MainActivity : ComponentActivity() {

    private val bleScanner = BleScanner(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LevelUPTheme {
                HomeScreen()
            }
        }

        // Start scanning when the app opens
        bleScanner.scanLeDevice()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == BleScanner.REQUEST_CODE) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                bleScanner.scanLeDevice()
            } else {
                // Show a message to the user
            }
        }
    }
}
