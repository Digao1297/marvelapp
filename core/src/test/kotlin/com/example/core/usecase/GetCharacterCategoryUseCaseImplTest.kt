package com.example.core.usecase

import com.example.core.data.repositories.CharactersRepository
import com.example.core.usecase.base.ResultStatus
import com.example.testing.MainCoroutineRule
import com.example.testing.model.CharactersFactory
import com.example.testing.model.ComicsFactory
import com.example.testing.model.EventsFactory
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetCharacterCategoryUseCaseImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: CharactersRepository

    private lateinit var getCharacterCategoryUseCase: GetCharacterCategoryUseCase
    private val character = CharactersFactory().create(CharactersFactory.Hero.ThreeDMan)
    private val comics = listOf(ComicsFactory().create(ComicsFactory.FakeComic.FakeComic1))
    private val events = listOf(EventsFactory().create(EventsFactory.FakeEvent.FakeEvent1))

    @Before
    fun setUp() {
        getCharacterCategoryUseCase = GetCharacterCategoryUseCaseImpl(
            repository, mainCoroutineRule.testDispatcherProvider
        )
    }

    @Test
    fun `should return Success from ResultStatus when get both requests return success`() =
        runTest {

            //Arrange
            whenever(repository.getComics(character.id)).thenReturn(comics)
            whenever(repository.getEvents(character.id)).thenReturn(events)

            //Act
            val result = getCharacterCategoryUseCase.invoke(
                GetCharacterCategoryUseCase.GetCategoriesParams(character.id)
            )

            //Assert
            val resultList = result.toList()
            assertEquals(ResultStatus.Loading, resultList[0])
            assertTrue(resultList[1] is ResultStatus.Success)

        }

    @Test
    fun `should return Error from ResultStatus when get events request returns error`() = runTest {
        //Arrange
        whenever(repository.getComics(character.id)).thenReturn(comics)
        whenever(repository.getEvents(character.id)).thenAnswer { throw Throwable() }

        //Act
        val result = getCharacterCategoryUseCase.invoke(
            GetCharacterCategoryUseCase.GetCategoriesParams(character.id)
        )

        //Assert
        val resultList = result.toList()
        assertEquals(ResultStatus.Loading, resultList[0])
        assertTrue(resultList[1] is ResultStatus.Error)
    }

    @Test
    fun `should return Error from ResultStatus when get comics request returns error`() = runTest {
        //Arrange
        whenever(repository.getComics(character.id)).thenAnswer { throw Throwable() }

        //Act
        val result = getCharacterCategoryUseCase.invoke(
            GetCharacterCategoryUseCase.GetCategoriesParams(character.id)
        )

        //Assert
        val resultList = result.toList()
        assertEquals(ResultStatus.Loading, resultList[0])
        assertTrue(resultList[1] is ResultStatus.Error)
    }
}