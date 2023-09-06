package com.example.jetpackcomposetutorial.common.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposetutorial.common.data.model.UserData
import com.example.jetpackcomposetutorial.common.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository): ViewModel() {
    val userListLivedata: StateFlow<List<UserData>>
        get() = _userListLivedata
    var _userListLivedata = MutableStateFlow<List<UserData>>(listOf())
    val userLiveData: MutableStateFlow<UserData?> by lazy {
        _userLiveData
    }
    val _userLiveData = MutableStateFlow<UserData?>(null)

    fun fetchUserData() {
        viewModelScope.launch {
            //either fetchUserDataFlow() or fetchUserData() will work
            userRepository.fetchUserData().collect {
                _userListLivedata.value = it
            }
        }
    }

    fun getUserDataById(userId: String) {
        _userListLivedata.value.find {
            it.id.toString() == userId
        }?.let {
            _userLiveData.value = it
        }
    }

    fun getUserDetailsById(userId: String): UserData? {
        return _userListLivedata.value.find {
            it.id.toString() == userId
        }
    }
}