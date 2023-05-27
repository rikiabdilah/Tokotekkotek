package com.example.tokotekkotek.di

import android.content.Context
import androidx.room.Room
import com.example.tokotekkotek.data.local.AppDatabase
import com.example.tokotekkotek.data.local.dao.FavoriteDao
import com.example.tokotekkotek.repository.NetworkRepository
import com.example.tokotekkotek.repository.NetworkRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "toko.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideFavoriteDao(appDatabase: AppDatabase): FavoriteDao =
        appDatabase.favoriteDao()


}
