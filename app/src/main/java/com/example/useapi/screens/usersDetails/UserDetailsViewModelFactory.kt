package com.example.useapi.screens.usersDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.useapi.model.UserApi
import com.example.useapi.repository.NetworkUserRepository

class UserDetailsViewModelFactory (private val id: Long):
    ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserDetailsViewModel(id, NetworkUserRepository(UserApi.create())) as T
        }
    }