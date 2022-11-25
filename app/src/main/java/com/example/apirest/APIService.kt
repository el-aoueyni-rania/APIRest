package com.example.apirest

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface APIService {

    @GET("/Offres")
    suspend fun getOffres():Response<MutableList<Offre>>

    @DELETE("/Offres/{id}")
    suspend fun deleteOffre(@Path("id") id: Int?) : Response<ResponseBody>


    @POST("/Offres")
    fun addOffre(@Body info: Offre): retrofit2.Call<ResponseBody>


    @PUT("Offres/{code}")
    suspend fun updateOffre(@Body info: Offre, @Path("code") code : Int?): Response<ResponseBody>

}