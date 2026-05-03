package com.example.levelup

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

object ApiClient {
    val client = OkHttpClient()
    const val BASE_URL = "http://10.4.0.1:8000"

    fun sendSport(sport: String): String {
        val json = JSONObject().put("sport", sport).toString()
        val body = json.toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .url("$BASE_URL/sport")
            .post(body)
            .build()

        client.newCall(request).execute().use { response ->
            return response.body?.string() ?: "no response"
        }
    }
}