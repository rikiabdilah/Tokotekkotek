package com.example.tokotekkotek.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokotekkotek.model.ResponseNewsUpdateItem
import com.example.tokotekkotek.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val networkRepository: NetworkRepository) : ViewModel() {
    private val _allNewsUpdate = MutableLiveData<List<ResponseNewsUpdateItem>>()
    val allNewsUpdate: LiveData<List<ResponseNewsUpdateItem>> = _allNewsUpdate

    private val _detailNewsUpdate = MutableLiveData<ResponseNewsUpdateItem>()
    val detailNewsUpdate: LiveData<ResponseNewsUpdateItem> = _detailNewsUpdate


    fun getDataAllNewsUpdate() = viewModelScope.launch(Dispatchers.IO){
        val response = networkRepository.getAllDataNews()
        viewModelScope.launch(Dispatchers.Main){
            if(response.isNotEmpty()){
                _allNewsUpdate.postValue(response)
            }
        }
    }

    fun getDetailDataNewsUpdate(id : Int) = viewModelScope.launch(Dispatchers.IO) {
        val response = networkRepository.getDetailNews(id)
        viewModelScope.launch(Dispatchers.Main) {
            if (response.idNews.isNotEmpty()) {
                _detailNewsUpdate.postValue(response)
            }
        }
    }
}