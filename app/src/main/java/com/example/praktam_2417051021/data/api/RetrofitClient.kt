package com.example.praktam_2417051021.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL =
        "https://gist.githubusercontent.com/Krisvelix/43ced181ec5b6dfc6c6f827cdb00ebd5/raw/185dfa14aaad5fdbf48206e51bdd0bfcc755cbde/"

    val instance: ApiService by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    }

}