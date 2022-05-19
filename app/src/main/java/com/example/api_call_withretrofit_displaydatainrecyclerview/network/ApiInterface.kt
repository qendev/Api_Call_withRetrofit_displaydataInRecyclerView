package com.example.api_call_withretrofit_displaydatainrecyclerview.network

import com.example.api_call_withretrofit_displaydatainrecyclerview.model.MyDataItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("posts")
    fun getData(): Call<List<MyDataItem>>
}