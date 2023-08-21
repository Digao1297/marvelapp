package com.example.core.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.data.repositories.CharactersRepository
import com.example.core.data.repositories.StorageRepository
import com.example.testing.MainCoroutineRule
import com.example.testing.model.CharactersFactory
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetCharactersUseCaseImplTest {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var repository: CharactersRepository

    @Mock
    lateinit var storage: StorageRepository

    private lateinit var getCharactersUseCase: GetCharactersUseCase

    private val hero = CharactersFactory().create(CharactersFactory.Hero.ThreeDMan)

    private val fakePagingData = PagingData.from(listOf(hero))


    @Before
    fun setUp() {
        getCharactersUseCase = GetCharactersUseCaseImpl(repository, storage)
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case in called`() =
        runTest {

            val pagingConfig = PagingConfig(20)
            val orderBy = "ascending"
            val query = "spider"

            whenever(repository.getCachedCharacters(query, orderBy, pagingConfig))
                .thenReturn(
                    flowOf(fakePagingData)
                )

            whenever(storage.sorting)
                .thenReturn(
                    flowOf(orderBy)
                )

            val result = getCharactersUseCase.invoke(
                GetCharactersUseCase.GetCharactersParams(
                    query,
                    pagingConfig
                )
            )

            verify(repository).getCachedCharacters(query, orderBy, pagingConfig)

            Assert.assertNotNull(result.first())
        }

}