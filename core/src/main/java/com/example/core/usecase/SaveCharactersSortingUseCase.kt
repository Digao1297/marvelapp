package com.example.core.usecase

import com.example.core.data.mapper.SortingMapper
import com.example.core.data.repositories.StorageRepository
import com.example.core.usecase.base.CoroutinesDispatchers
import com.example.core.usecase.base.ResultStatus
import com.example.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface SaveCharactersSortingUseCase {

    operator fun invoke(params: Params): Flow<ResultStatus<Unit>>

    data class Params(
        val sorting: Pair<String, String>
    )
}

class SaveCharactersSortingUseCaseImpl @Inject constructor(
    private val repository: StorageRepository,
    private val sortingMapper: SortingMapper,
    private val dispatchers: CoroutinesDispatchers
) : UseCase<SaveCharactersSortingUseCase.Params, Unit>(),
    SaveCharactersSortingUseCase {
    override suspend fun doWork(params: SaveCharactersSortingUseCase.Params): ResultStatus<Unit> {
        return withContext(dispatchers.io()) {
            repository.saveSorting(sortingMapper.mapFromPair(params.sorting))
            ResultStatus.Success(Unit)
        }
    }
}