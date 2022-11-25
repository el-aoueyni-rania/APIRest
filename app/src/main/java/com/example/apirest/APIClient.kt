package com.example.apirest

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    private const val BASE_URL: String = "http://192.168.43.174:8088"
    private val gson : Gson by lazy {
        GsonBuilder().setLenient().create()
    }
    private val httpClient : OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }
    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
    val apiService :  APIService by lazy{
        retrofit.create(APIService::class.java)
    }
}