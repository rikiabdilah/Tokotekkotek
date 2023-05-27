package com.example.tokotekkotek.di

import com.example.tokotekkotek.repository.LocalRepository
import com.example.tokotekkotek.repository.LocalRepositoryImpl
import com.example.tokotekkotek.repository.NetworkRepository
import com.example.tokotekkotek.repository.NetworkRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideNetworkRepository(networkRepositoryImpl: NetworkRepositoryImpl): NetworkRepository

    @Binds
    abstract fun provideLocalRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository

}