package com.example.tokotekkotek.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokotekkotek.data.local.entity.FavoriteEntity
import kotlinx.coroutines.launch

class FavoriteViewModel : ViewModel() {
    private val _favorite = MutableLiveData<List<FavoriteEntity>>()
    val favorite: LiveData<List<FavoriteEntity>> = _favorite

//    private val _user = MutableLiveData<FirebaseUser?>()
//    val user: LiveData<FirebaseUser?> = _user

//    fun session() {
//        if (Firebase.auth.currentUser != null) {
//            _user.postValue(Firebase.auth.currentUser)
//        } else {
//            _user.postValue(null)
//        }
//    }
//
//    fun getAllFavorites(uuid: String) = viewModelScope.launch {
//        _favorite.postValue(local.getFavoriteTickets(uuid))
//    }
}