package com.example.retrofitpractice.service

import com.example.retrofitpractice.Model.Data
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("/user/{id}")
    fun selectUserProfile(@Path("id") id : String) : Call<Data>

    @GET("/user/all")
    fun getUserList() : Call<List<Data>>

    //핸드폰번호로, user정보 가져옴!
    @GET("/user/{phone}")
    fun selectPhoneUserProfile(@Path("phone") phone : String) : Call<Data>

    @POST("user/register")
    fun insertUserProfile(@Body data : Data) : Call<Data>
}