package com.example.marvelapp.framework

import com.example.core.data.repositories.FavoritesLocalDataSource
import com.example.core.data.repositories.FavoritesRepository
import com.example.core.domain.model.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val localDataSource: FavoritesLocalDataSource
) : FavoritesRepository {

    override fun getAll(): Flow<List<Character>> {
        return localDataSource.getAll()
    }

    override suspend fun save(character: Character) {
        localDataSource.save(character)
    }

    override suspend fun delete(character: Character) {
        localDataSource.delete(character)
    }
}