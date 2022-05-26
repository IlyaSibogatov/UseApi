package com.example.useapi.model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users?delay=2")
    fun getUsers(): Call<ReqresUserListApiResponse>

    @GET("users/{id}?delay=2")
    fun getUserById(@Path("id") id: Long): Call<ReqresUserApiResponse>

    companion object {
        const val BASE_URL = "https://reqres.in/api/"

        fun create(): UserApi {
            val retrofit = Retrofit
                .Builder()
                .addConverterFactory(
                    GsonConverterFactory
                        .create()
                )
                .baseUrl(BASE_URL).build()
            return retrofit.create(UserApi::class.java)
        }
    }
}