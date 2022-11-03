package com.example.core.data.repositories

import androidx.paging.PagingSource
import com.example.core.domain.model.Character

interface CharactersRepository {
    fun getCharacters(query: String): PagingSource<Int, Character>
}