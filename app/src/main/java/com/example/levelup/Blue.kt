    package com.example.levelup

import android.app.Activity
import android.os.Build
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class BleScanner(val activity: Activity) {
    companion object {
        const val REQUEST_CODE = 1
    }

    private val requiredPermissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        arrayOf(
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_CONNECT
        )
    } else {
        arrayOf(
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    fun hasPermissions(): Boolean {
        return requiredPermissions.all {
            ContextCompat.checkSelfPermission(activity, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    fun requestPermissions() {
        val missing = requiredPermissions.filter {
            ContextCompat.checkSelfPermission(activity, it) != PackageManager.PERMISSION_GRANTED
        }.toTypedArray()

        if (missing.isNotEmpty()) {
            ActivityCompat.requestPermissions(activity, missing, REQUEST_CODE)
        }
    }

    fun scanLeDevice() {
        if (!hasPermissions()) {
            requestPermissions()
            return
        }

        // Je sais pas ce que je fous
    }
}