package com.example.tokotekkotek.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokotekkotek.model.ResponseNewsUpdateItem
import com.example.tokotekkotek.model.ResponseUserTransHistoryItem
import com.example.tokotekkotek.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryTransactionViewModel @Inject constructor(private val networkRepository: NetworkRepository) : ViewModel() {
    private val _allHistoryTransaction = MutableLiveData<List<ResponseUserTransHistoryItem>>()
    val allHistoryTransaction: LiveData<List<ResponseUserTransHistoryItem>> = _allHistoryTransaction

    fun getDataAllHistoryTransaction(id : Int) = viewModelScope.launch(Dispatchers.IO){
        val response = networkRepository.getAllHistoryTransaction(id)
        viewModelScope.launch(Dispatchers.Main){
            if(response.isNotEmpty()){
                _allHistoryTransaction.postValue(response)
            }
        }
    }

}