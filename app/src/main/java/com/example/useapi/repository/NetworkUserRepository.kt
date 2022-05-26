package com.example.useapi.repository

import com.example.useapi.model.ReqresUserApiResponse
import retrofit2.Callback
import com.example.useapi.model.ReqresUserListApiResponse
import com.example.useapi.model.UserApi

class NetworkUserRepository (private val userApi: UserApi){

    fun getUsers(callback: Callback<ReqresUserListApiResponse>){
        userApi.getUsers().enqueue(callback)
    }

    fun getUserById(id: Long, callback: Callback<ReqresUserApiResponse>){
        userApi.getUserById(id).enqueue(callback)
    }
}