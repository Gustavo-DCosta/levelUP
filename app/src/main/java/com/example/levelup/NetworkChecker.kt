package com.example.levelup

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import android.os.Build

class NetworkChecker(private val context: Context) {
    fun isOnLevelUpNetwork(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            checkWithConnectivityManager()
        } else {
            checkWithWifiManager()
        }
    } // ← this was missing

    private fun checkWithConnectivityManager(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // Instead of activeNetwork, look at all available networks
        val allNetworks = connectivityManager.allNetworks

        for (network in allNetworks) {
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            if (capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {

                // On API 29+, you need the ACCESS_FINE_LOCATION permission
                // to read the SSID via WifiManager.
                val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                val info = wifiManager.connectionInfo
                val ssid = info.ssid

                android.util.Log.d("NetworkChecker", "Checking Wi-Fi Network. Found SSID: $ssid")

                if (ssid == "\"Freebox-AD1FD6\"") {
                    return true
                }
            }
        }
        return false
    }

    private fun checkWithWifiManager(): Boolean {
        val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val ssid = wifiManager.connectionInfo.ssid
        android.util.Log.d("NetworkChecker", "Current SSID: $ssid")
        return ssid == "\"Freebox-AD1FD6\""
    }
}