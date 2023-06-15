package com.example.core.usecase

import androidx.paging.PagingConfig
import com.example.core.data.repositories.CharactersRepository
import com.example.core.usecase.params.GetCharactersParams
import com.example.testing.MainCoroutineRule
import com.example.testing.model.CharactersFactory
import com.example.testing.pagingsource.PagingSourceFactory
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCharactersUseCaseImplTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var repository: CharactersRepository

    private lateinit var getCharactersUseCase: GetCharactersUseCase

    private val hero = CharactersFactory().create(CharactersFactory.Hero.ThreeDMan)

    private val fakePagingSource = PagingSourceFactory().create(listOf(hero))


    @Before
    fun setUp() {
        getCharactersUseCase = GetCharactersUseCaseImpl(repository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should validate flow paging data creation when invoke from use case in called`() =
        runTest {

            whenever(repository.getCharacters("")).thenReturn(fakePagingSource)

            val result = getCharactersUseCase.invoke(GetCharactersParams("", PagingConfig(20)))

            Assert.assertNotNull(result.first())
        }

}