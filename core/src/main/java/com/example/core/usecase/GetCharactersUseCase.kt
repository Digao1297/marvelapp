package com.example.core.usecase

import androidx.paging.PagingData
import com.example.core.domain.model.Character
import com.example.core.usecase.params.GetCharactersParams
import kotlinx.coroutines.flow.Flow

interface GetCharactersUseCase {
    operator fun invoke(params: GetCharactersParams): Flow<PagingData<Character>>
}