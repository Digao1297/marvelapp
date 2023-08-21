package com.example.core.data.repositories

import kotlinx.coroutines.flow.Flow

interface StorageLocalDataSource {
    val sorting: Flow<String>

    suspend fun saveSorting(sorting: String)
}