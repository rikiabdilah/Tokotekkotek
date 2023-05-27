package com.example.tokotekkotek.repository

import com.example.tokotekkotek.model.*
import com.example.tokotekkotek.network.RestfulApi
import javax.inject.Inject

interface NetworkRepository {

    suspend fun getDataUsers(): List<ResponseDataUserItem>

    suspend fun loginUser(
        email : String,
        password : String
    ) : List<ResponseDataUserItem>

    suspend fun registUser(
        data : DataUserRegist
    ) : ResponseDataUserItem

    //cart
    suspend fun getDataCart(
        id : Int
    ) : List<ResponseDataCartItem>

    //favourite
    suspend fun getDataFavourite(
        id: Int
    ) : List<ResponseDataFavouriteItem>
    //newsupdate
    suspend fun getAllDataNews() : List<ResponseNewsUpdateItem>

    suspend fun getDetailNews(
        idNews : Int
    ) : ResponseNewsUpdateItem

    //product
    suspend fun getAllDataProduct(
        idCategory : Int
    ) : List<ResponseDataProductItem>

    suspend fun getDetailProducts(
        idCategory: Int,
        idProduct : Int
    ) : ResponseDataProductItem
    //slider
    suspend fun getDataSlides() : List<ResponseDataSlidersItem>
}


class NetworkRepositoryImpl @Inject constructor(private val api : RestfulApi) : NetworkRepository{
    override suspend fun getDataUsers(): List<ResponseDataUserItem> = api.getDataUsers()
    override suspend fun loginUser(email: String, password: String): List<ResponseDataUserItem> = api.loginUser(email, password)
    override suspend fun registUser(data: DataUserRegist): ResponseDataUserItem = api.registerUser(data)
    override suspend fun getDataCart(id: Int): List<ResponseDataCartItem> = api.getDataCart(id)
    override suspend fun getDataFavourite(id: Int): List<ResponseDataFavouriteItem> = api.getDataFavourite(id)

    //news
    override suspend fun getAllDataNews(): List<ResponseNewsUpdateItem> = api.getAllDataNews()
    override suspend fun getDetailNews(idNews: Int): ResponseNewsUpdateItem = api.getDetailsNews(idNews)

    //product
    override suspend fun getAllDataProduct(idCategory: Int): List<ResponseDataProductItem> = api.getAllProduct(idCategory)
    override suspend fun getDetailProducts(idCategory: Int, idProduct: Int): ResponseDataProductItem = api.getDetailProduct(idCategory, idProduct)
    override suspend fun getDataSlides(): List<ResponseDataSlidersItem> = api.getSlides()

}