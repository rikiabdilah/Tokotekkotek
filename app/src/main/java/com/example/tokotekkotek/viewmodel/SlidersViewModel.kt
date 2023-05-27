package com.example.tokotekkotek.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokotekkotek.model.ResponseDataSlidersItem
import com.example.tokotekkotek.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SlidersViewModel @Inject constructor(private val networkRepository: NetworkRepository) : ViewModel(){
    private val _allSliders = MutableLiveData<List<ResponseDataSlidersItem>>()
    val allSliders: LiveData<List<ResponseDataSlidersItem>> = _allSliders

    fun getDataAllSliders() = viewModelScope.launch(Dispatchers.IO){
        val response = networkRepository.getDataSlides()
        viewModelScope.launch(Dispatchers.Main){
            if(response.isNotEmpty()){
                _allSliders.postValue(response)
            }
        }
    }

}