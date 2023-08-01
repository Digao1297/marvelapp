package com.example.core.data.repositories

import com.example.core.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

     fun getAll(): Flow<List<Character>>

    suspend fun save(character: Character)

    suspend fun delete(character: Character)
}