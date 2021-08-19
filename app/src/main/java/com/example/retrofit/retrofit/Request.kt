package com.example.retrofit.retrofit

import com.example.retrofit.model.MyModel
import retrofit2.Response
import retrofit2.http.GET


interface Request {

    @GET("random_ten/")
    suspend fun getDataRequest():Response<List<MyModel>>
}