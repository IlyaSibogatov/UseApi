package com.example.useapi.screens.UsersList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.useapi.model.UserApi
import com.example.useapi.repository.NetworkUserRepository

class UsersListViewModelFactory :
    ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UsersListViewModel(NetworkUserRepository(UserApi.create())) as T
        }
    }