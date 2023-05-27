package com.example.tokotekkotek.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokotekkotek.model.ResponseDataProductItem
import com.example.tokotekkotek.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val networkRepository: NetworkRepository) : ViewModel(){

    private val _allNewProduct = MutableLiveData<List<ResponseDataProductItem>>()
    val allNewProduct: LiveData<List<ResponseDataProductItem>> = _allNewProduct

    private val _allSecondProduct = MutableLiveData<List<ResponseDataProductItem>>()
    val allSecondProduct: LiveData<List<ResponseDataProductItem>> = _allSecondProduct

    private val _detailNewProduct = MutableLiveData<ResponseDataProductItem>()
    val detailNewProduct: LiveData<ResponseDataProductItem> = _detailNewProduct

    private val _detailSecondProduct = MutableLiveData<ResponseDataProductItem>()
    val detailSecondProduct: LiveData<ResponseDataProductItem> = _detailSecondProduct


    fun getDataAllProduct(idCat : Int) = viewModelScope.launch(Dispatchers.IO){
        val response = networkRepository.getAllDataProduct(idCat)
        viewModelScope.launch(Dispatchers.Main){
            if(response.isNotEmpty() && idCat == 1){
                _allNewProduct.postValue(response)
            }else if(response.isNotEmpty() && idCat == 2){
                _allSecondProduct.postValue(response)
            }
        }
    }

    fun getDetailDataProduct(idCat : Int, idProduct : Int) = viewModelScope.launch(Dispatchers.IO){
        val response = networkRepository.getDetailProducts(idCat, idProduct)
        viewModelScope.launch(Dispatchers.Main){
            if(response.idProduct.isNotEmpty() && idCat == 1){
                _detailNewProduct.postValue(response)
            }else if(response.idProduct.isNotEmpty() && idCat == 2){
                _detailSecondProduct.postValue(response)
            }
        }
    }
}