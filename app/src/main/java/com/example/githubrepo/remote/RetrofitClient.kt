package com.example.githubrepo.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        private const val BASE_URL = "https://api.github.com"

        @Volatile
        private var instance: Retrofit? = null

        fun getInstance(): Retrofit {
            if (instance == null) {
                synchronized(this) {
                    instance = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                }
            }
            return instance!!
        }
    }
}