package com.example.tokotekkotek.network

import com.example.tokotekkotek.model.*
import retrofit2.Response
import retrofit2.http.*

interface RestfulApi {
    @GET("users")
    suspend fun getDataUsers() : List<ResponseDataUserItem>

    @PUT("users/{id}")
    suspend fun updateDataUser(
        @Path("id") id : Int,
        @Body data : ResponseDataUserItem
    ) : ResponseDataUserItem

    @GET("users/{id}")
    suspend fun getDetailUser(
        @Path("id") id : Int,
    ) : ResponseDataUserItem

    @GET("users")
    suspend fun loginUser(
        @Query("email") email : String,
        @Query("password") password : String
    ) : List<ResponseDataUserItem>

    @POST("users")
    suspend fun registerUser(
        @Body data: DataUserRegist
    ) : ResponseDataUserItem

    //endpoint history transaction
    @GET("users/{id}/transhistory")
    suspend fun getDataHistoryTransaction(
        @Path("id") id : Int,
    ) : List<ResponseUserTransHistoryItem>

    @POST("users/{id}/transhistory")
    suspend fun insertDataHistoryTransaction(
        @Path("id") id : Int,
        @Body data : ResponseUserTransHistoryItem
    ) : ResponseUserTransHistoryItem

    //endpoint cart
    @GET("users/{id}/cart")
    suspend fun getDataCart(
        @Path("id") id : Int,
    ) : List<ResponseDataCartItem>

    @POST("users/{id}/cart")
    suspend fun insertDataCart(
        @Path("id") id : Int,
        @Body data : ResponseDataUserItem
    ) : Response<ResponseDataCartItem>

    @PUT("users/{id}/cart")
    suspend fun updateDataCart(
        @Path("id") id : Int,
        @Body data : ResponseDataUserItem
    ) : Response<ResponseDataCartItem>

    @DELETE("users/{id}/cart")
    suspend fun deleteDataCart(
        @Path("id") id : Int,
    ) : Response<ResponseDataCartItem>


    //favorite
    @GET("users/{id}/favourite")
    suspend fun getDataFavourite(
        @Path("id") id : Int
    ) : List<ResponseDataFavouriteItem>

    @POST("users/{id}/favourite")
    suspend fun insertDataFavourite(
        @Path("id") id : Int,
        @Body data : ResponseDataFavouriteItem
    ) : Response<ResponseDataFavouriteItem>

    @DELETE("users/{idUser}/favourite/{idFav}")
    suspend fun deleteDataFavourite(
        @Path("idUser") idUser : Int,
        @Path("idFav") idFav : Int
    ) : Response<ResponseDataFavouriteItem>

    //endpoint news update
    @GET("news_update")
    suspend fun getAllDataNews() : List<ResponseNewsUpdateItem>

    @GET("news_update/{id}")
    suspend fun getDetailsNews(
        @Path("id") id :Int
    ) : ResponseNewsUpdateItem

    //endpoint category product
    @GET("category_product")
    suspend fun getCategoryProduct() : List<ResponseCategoryProductItem>

    @GET("category_product/{id}")
    suspend fun getDetailCategoryProduct(
        @Path("id") id : Int
    ) : ResponseCategoryProductItem

    //product
    @GET("category_product/{id}/products")
    suspend fun getAllProduct(
        @Path("id") id: Int
    ) : List<ResponseDataProductItem>

    @GET("category_product/{idCategory}/products/{idProduct}")
    suspend fun getDetailProduct(
        @Path("idCategory") idCategory: Int,
        @Path("idProduct") idProduct: Int
    ) : ResponseDataProductItem

    //slide
    @GET("sliders")
    suspend fun getSlides() : List<ResponseDataSlidersItem>

}