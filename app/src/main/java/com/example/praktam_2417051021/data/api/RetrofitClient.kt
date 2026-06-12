package com.example.praktam_2417051021.data.api

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val Context.dataStore by preferencesDataStore("user_pref")
    private const val BASE_URL =
        "https://gist.githubusercontent.com/Krisvelix/43ced181ec5b6dfc6c6f827cdb00ebd5/raw/5e8b47c12cf58ccd9552996c41b84f8b4aa0fb75/"

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}