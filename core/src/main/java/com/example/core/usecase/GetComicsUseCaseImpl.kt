package com.example.core.usecase

import com.example.core.data.repositories.CharactersRepository
import com.example.core.domain.model.Comic
import com.example.core.usecase.base.ResultStatus
import com.example.core.usecase.base.UseCase
import com.example.core.usecase.params.GetComicsParams
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetComicsUseCaseImpl @Inject constructor(
    private val repository: CharactersRepository,
) : GetComicsUseCase,UseCase<GetComicsParams, List<Comic>>() {

    override suspend fun doWork(params: GetComicsParams): ResultStatus<List<Comic>> {
        val comics = repository.getComics(params.characterId)
        return ResultStatus.Success(comics)
    }
}