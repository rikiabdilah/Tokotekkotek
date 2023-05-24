package com.example.tokotekkotek.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tokotekkotek.data.local.entity.FavoriteEntity

@Dao
interface FavoriteDao {

    @Query("SELECT * From favorite_table where username = :username")
    suspend fun getFavoriteCart(username: String): List<FavoriteEntity>

    @Query("SELECT EXISTS(SELECT * FROM favorite_table WHERE id_barang = : id_barang AND username = :username)")
    suspend fun isFavorit(id_barang: Int, username: String): Boolean

    @Insert
    suspend fun addFavorite(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM favorite_table WHERE id_barang = :id_barang AND username")
    suspend fun deleteFavorite(id_barang: Int, username: String)


}