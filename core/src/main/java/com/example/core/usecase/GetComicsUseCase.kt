package com.example.core.usecase

import com.example.core.domain.model.Comic
import com.example.core.usecase.base.ResultStatus
import com.example.core.usecase.params.GetComicsParams
import kotlinx.coroutines.flow.Flow

interface GetComicsUseCase {
    operator fun invoke(params: GetComicsParams): Flow<ResultStatus<List<Comic>>>
}