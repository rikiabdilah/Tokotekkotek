package com.example.tokotekkotek.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokotekkotek.model.DataUserRegist
import com.example.tokotekkotek.model.ResponseDataUserItem
import com.example.tokotekkotek.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val networkRepository: NetworkRepository): ViewModel() {
    private val _users = MutableLiveData<List<ResponseDataUserItem>>()
    val users: LiveData<List<ResponseDataUserItem>> = _users

    private val _usersRegist = MutableLiveData<ResponseDataUserItem?>()
    val usersRegist: LiveData<ResponseDataUserItem?> = _usersRegist

    fun getDataAllUsers() = viewModelScope.launch(Dispatchers.IO){
        val response = networkRepository.getDataUsers()
        viewModelScope.launch(Dispatchers.Main){
            if(response.isNotEmpty()){
                _users.postValue(response)
            }
        }
    }

    fun registDataUser(data : DataUserRegist) = viewModelScope.launch(Dispatchers.IO){
        val response = networkRepository.registUser(data)
        viewModelScope.launch(Dispatchers.Main){
            if(response.toString().isNotEmpty()){
                _usersRegist.postValue(response)
            }else{
                _usersRegist.postValue(null)
            }
        }
    }
}