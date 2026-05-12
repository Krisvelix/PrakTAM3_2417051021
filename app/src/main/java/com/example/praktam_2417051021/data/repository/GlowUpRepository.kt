package com.example.praktam_2417051021.data.repository

import com.example.praktam_2417051021.data.api.RetrofitClient
import com.example.praktam_2417051021.data.model.GlowUp

class GlowUpRepository {

    suspend fun getGlowUp(): List<GlowUp> {

        return try {
            RetrofitClient.instance.getGlowUp()
        } catch (e: Exception) {
            emptyList()
        }

    }

}