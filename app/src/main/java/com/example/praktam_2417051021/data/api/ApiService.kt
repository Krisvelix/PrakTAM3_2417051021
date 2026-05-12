package com.example.praktam_2417051021.data.api

import com.example.praktam_2417051021.data.model.GlowUp
import retrofit2.http.GET

interface ApiService {

    @GET("glowup.json")
    suspend fun getGlowUp(): List<GlowUp>

}