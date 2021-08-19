package com.example.retrofit.repository

import com.example.retrofit.model.MyModel
import com.example.retrofit.retrofit.RetrofitInstance
import retrofit2.Response

class MyRepository() {
    suspend fun getData(): Response<List<MyModel>>{
        return RetrofitInstance.getInstance().getDataRequest()
    }


}