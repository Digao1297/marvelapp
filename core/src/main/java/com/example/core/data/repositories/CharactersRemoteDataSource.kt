package com.example.core.data.repositories

interface CharactersRemoteDataSource< out T> {
    suspend fun fetchCharacters(queries: Map<String, String>): T
}