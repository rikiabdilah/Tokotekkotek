package com.example.tokotekkotek.repository

import com.example.tokotekkotek.model.ResponseDataFavouriteItem
import com.example.tokotekkotek.model.ResponseDataProductItem
import com.example.tokotekkotek.network.RestfulApi
import javax.inject.Inject


interface NetworkRepository {

    suspend fun getAllProduct(
        page: Int = 1
    ): ResponseDataProductItem

//    suspend fun getDetailMovie(
//        movieId: Int
//    ): DetailMovieResponse
//}
//
//class NetworkRepositoryImpl  : NetworkRepository {
//    override suspend fun getAllMoviesNowPlaying(page: Int): MovieResponse =
//        apiService.getAllMoviesNowPlaying()
//
//    override suspend fun getDetailMovie(movieId: Int): DetailMovieResponse =
//        apiService.getDetailMovie(movieId)

}