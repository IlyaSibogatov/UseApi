package com.example.useapi.screens.UsersList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.useapi.ResultState
import com.example.useapi.repository.NetworkUserRepository
import com.example.useapi.model.ReqresUser
import com.example.useapi.model.ReqresUserListApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class UsersListViewModel(private val userRepository: NetworkUserRepository) : ViewModel() {

    private val _users = MutableLiveData<ResultState<List<ReqresUser>>>()
    val users: LiveData<ResultState<List<ReqresUser>>> = _users

    init {
        loadUsers()
    }

    fun loadUsers() {
        _users.value = ResultState.Loading()

        userRepository.getUsers(object : Callback<ReqresUserListApiResponse> {
            override fun onResponse(
                call: Call<ReqresUserListApiResponse>,
                response: Response<ReqresUserListApiResponse>
            ) {
                val result = response.body()?.let { responseBody ->
                    val userList = responseBody.data.map {
                        ReqresUser(
                            it.id,
                            it.email,
                            it.first_name,
                            it.last_name,
                            it.avatar
                        )
                    }

                    ResultState.Success(userList)

                } ?: ResultState.Error(RuntimeException("Response body is null"))
                _users.value = result
            }

            override fun onFailure(call: Call<ReqresUserListApiResponse>, t: Throwable) {
                _users.value = ResultState.Error(t)
            }
        })
    }
}