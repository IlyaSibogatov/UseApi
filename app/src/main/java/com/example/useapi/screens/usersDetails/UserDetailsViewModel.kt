package com.example.useapi.screens.usersDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.useapi.ResultState
import com.example.useapi.model.ReqresUser
import com.example.useapi.model.ReqresUserApiResponse
import com.example.useapi.repository.NetworkUserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class UserDetailsViewModel(
    id: Long,
    private val userRepository: NetworkUserRepository
) : ViewModel() {

    private val _user = MutableLiveData<ResultState<ReqresUser>>()
    val user: LiveData<ResultState<ReqresUser>> = _user

    init {
        loadUser(id)
    }

    fun loadUser(id: Long) {
        
        _user.value = ResultState.Loading()

        userRepository.getUserById(id, object : Callback<ReqresUserApiResponse> {
            override fun onResponse(
                call: Call<ReqresUserApiResponse>,
                response: Response<ReqresUserApiResponse>
            ) {
                val responseBody = response.body()
                if (responseBody == null) {
                    _user.value = ResultState.Error(RuntimeException("ERROR"))
                    return
                } else {
                    val user = ReqresUser(
                        responseBody.data.id,
                        responseBody.data.email,
                        responseBody.data.first_name,
                        responseBody.data.last_name,
                        responseBody.data.avatar
                    )
                    _user.value = ResultState.Success(user)
                }
            }

            override fun onFailure(call: Call<ReqresUserApiResponse>, t: Throwable) {
                _user.value = ResultState.Error(t)
            }
        })
    }
}