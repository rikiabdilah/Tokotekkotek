package com.example.tokotekkotek.repository

import com.example.tokotekkotek.data.local.dao.FavoriteDao
import com.example.tokotekkotek.data.local.entity.FavoriteEntity
import javax.inject.Inject


interface LocalRepository {
    suspend fun getFavoriteCart(username: String): List<FavoriteEntity>
    suspend fun isFavorite(id_barang: Int, uuid_user: String): Boolean
    suspend fun addFavorite(favoriteEntity: FavoriteEntity)
    suspend fun deleteFavorite(id_barang: Int, uuid_user: String)
}

class LocalRepositoryImpl @Inject constructor(private val favoriteDao: FavoriteDao) :
    LocalRepository {
    override suspend fun getFavoriteCart(username: String): List<FavoriteEntity> =
        favoriteDao.getFavoriteCart(username)

    override suspend fun isFavorite(id_barang: Int, username: String): Boolean =
        favoriteDao.isFavorit(id_barang, username)

    override suspend fun addFavorite(favoriteEntity: FavoriteEntity) =
        favoriteDao.addFavorite(favoriteEntity)

    override suspend fun deleteFavorite(id_barang: Int, username: String) =
        favoriteDao.deleteFavorite(id_barang, username)

}