package com.example.marvelapp.framework.di

import com.example.core.data.repositories.FavoritesLocalDataSource
import com.example.core.data.repositories.FavoritesRepository
import com.example.marvelapp.framework.FavoritesRepositoryImpl
import com.example.marvelapp.framework.local.RoomFavoritesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FavoritesRepositoryModule {
    @Binds
    fun bindFavoritesRepository(repository: FavoritesRepositoryImpl): FavoritesRepository

    @Binds
    fun bindLocalDataSource(
        dataSource: RoomFavoritesDataSource
    ): FavoritesLocalDataSource

}