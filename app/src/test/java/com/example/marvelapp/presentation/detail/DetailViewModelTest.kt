package com.example.marvelapp.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.core.domain.model.Comic
import com.example.core.domain.model.Event
import com.example.core.usecase.AddFavoriteUseCase
import com.example.core.usecase.CheckFavoriteUseCase
import com.example.core.usecase.GetCharacterCategoryUseCase
import com.example.core.usecase.RemoveFavoriteUseCase
import com.example.core.usecase.base.ResultStatus
import com.example.marvelapp.R
import com.example.testing.MainCoroutineRule
import com.example.testing.model.CharactersFactory
import com.example.testing.model.ComicsFactory
import com.example.testing.model.EventsFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.isA
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class DetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var getCharacterCategoriesUseCase: GetCharacterCategoryUseCase

    @Mock
    private lateinit var addFavoriteUseCase: AddFavoriteUseCase

    @Mock
    private lateinit var checkFavoriteUseCase: CheckFavoriteUseCase

    @Mock
    private lateinit var removeFavoriteUseCase: RemoveFavoriteUseCase

    @Mock
    private lateinit var uiStateObserver: Observer<UiActionStateLiveData.UiState>

    @Mock
    private lateinit var favoriteUiStateObserver: Observer<FavoriteUiActionStateLiveData.UiState>

    private lateinit var detailViewModel: DetailViewModel
    private val character = CharactersFactory().create(CharactersFactory.Hero.ThreeDMan)
    private val comics = listOf(ComicsFactory().create(ComicsFactory.FakeComic.FakeComic1))
    private val events = listOf(EventsFactory().create(EventsFactory.FakeEvent.FakeEvent1))


    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(
            getCharacterCategoryUseCase = getCharacterCategoriesUseCase,
            addFavoriteUseCase = addFavoriteUseCase,
            checkFavoriteUseCase = checkFavoriteUseCase,
            removeFavoriteUseCase = removeFavoriteUseCase,
            mainCoroutineRule.testDispatcherProvider,
        ).apply {
            categories.state.observeForever(uiStateObserver)
            favorite.state.observeForever(favoriteUiStateObserver)
        }
    }

    @Test
    fun `should notify uiState with Success from UiState when get character categories returns success`() =
        runTest {

            // Arrange
            whenever(getCharacterCategoriesUseCase.invoke(any()))
                .thenReturn(
                    flowOf(
                        ResultStatus.Success(
                            comics to events
                        )
                    )
                )

            // Act
            detailViewModel.categories.load(character.id)

            //Assert
            verify(uiStateObserver).onChanged(isA<UiActionStateLiveData.UiState.Success>())

            val uiStateSuccess =
                detailViewModel.categories.state.value as UiActionStateLiveData.UiState.Success
            val categoriesParentList = uiStateSuccess.detailParentList

            Assert.assertEquals(2, categoriesParentList.size)
            Assert.assertEquals(
                R.string.details_comics_category,
                categoriesParentList[0].categoryStringResId
            )
            Assert.assertEquals(
                R.string.details_events_category,
                categoriesParentList[1].categoryStringResId
            )

        }

    @Test
    fun `should notify uiState with Success from UiState when get character categories returns only comics`() =
        runTest {
            // Arrange
            whenever(getCharacterCategoriesUseCase.invoke(any()))
                .thenReturn(
                    flowOf(
                        ResultStatus.Success(
                            comics to emptyList<Event>()
                        )
                    )
                )
            //Act
            detailViewModel.categories.load(character.id)

            //Assert
            verify(uiStateObserver).onChanged(isA<UiActionStateLiveData.UiState.Success>())

            val uiStateSuccess =
                detailViewModel.categories.state.value as UiActionStateLiveData.UiState.Success
            val categoriesParentList = uiStateSuccess.detailParentList

            Assert.assertEquals(1, categoriesParentList.size)
            Assert.assertEquals(
                R.string.details_comics_category,
                categoriesParentList[0].categoryStringResId
            )
        }

    @Test
    fun `should notify uiState with Success from UiState when get character categories returns only events`() =
        runTest {
            // Arrange
            whenever(getCharacterCategoriesUseCase.invoke(any()))
                .thenReturn(
                    flowOf(
                        ResultStatus.Success(
                            emptyList<Comic>() to events
                        )
                    )
                )
            //Act
            detailViewModel.categories.load(character.id)

            //Assert
            verify(uiStateObserver).onChanged(isA<UiActionStateLiveData.UiState.Success>())

            val uiStateSuccess =
                detailViewModel.categories.state.value as UiActionStateLiveData.UiState.Success
            val categoriesParentList = uiStateSuccess.detailParentList

            Assert.assertEquals(1, categoriesParentList.size)
            Assert.assertEquals(
                R.string.details_events_category,
                categoriesParentList[0].categoryStringResId
            )
        }

    @Test
    fun `should notify uiState with Empty from UiState when get character categories returns an empty result list`() =
        runTest {
            // Arrange
            whenever(getCharacterCategoriesUseCase.invoke(any()))
                .thenReturn(
                    flowOf(
                        ResultStatus.Success(
                            emptyList<Comic>() to emptyList<Event>()
                        )
                    )
                )
            //Act
            detailViewModel.categories.load(character.id)
            //Assert
            verify(uiStateObserver).onChanged(isA<UiActionStateLiveData.UiState.Empty>())
        }

    @Test
    fun `should notify uiState with Error from UiState when get character categories returns an exception`() =
        runTest {
            // Arrange
            whenever(getCharacterCategoriesUseCase.invoke(any()))
                .thenReturn(
                    flowOf(
                        ResultStatus.Error(
                            Throwable()
                        )
                    )
                )
            //Act
            detailViewModel.categories.load(character.id)
            //Assert
            verify(uiStateObserver).onChanged(isA<UiActionStateLiveData.UiState.Error>())

        }


    @Test
    fun `should notify favorite_uiState with filled favorite icon when check favorite returns true`() =
        runTest {
            // Arrange
            whenever(checkFavoriteUseCase.invoke(any()))
                .thenReturn(
                    flowOf(
                        ResultStatus.Success(true)
                    )
                )
            // Action
            detailViewModel.favorite.checkFavorite(character.id)
            // Assert
            verify(favoriteUiStateObserver).onChanged(
                isA<FavoriteUiActionStateLiveData.UiState.Icon>()
            )

            val uiStateSuccess =
                detailViewModel.favorite.state.value as FavoriteUiActionStateLiveData.UiState.Icon

            Assert.assertEquals(R.drawable.ic_favorite_checked, uiStateSuccess.icon)
        }

    @Test
    fun `should notify favorite_uiState with not filled favorite icon when check favorite returns false`() =
        runTest {
            // Arrange
            whenever(checkFavoriteUseCase.invoke(any()))
                .thenReturn(
                    flowOf(
                        ResultStatus.Success(false)
                    )
                )
            // Action
            detailViewModel.favorite.checkFavorite(character.id)
            // Assert
            verify(favoriteUiStateObserver).onChanged(
                isA<FavoriteUiActionStateLiveData.UiState.Icon>()
            )

            val uiStateSuccess =
                detailViewModel.favorite.state.value as FavoriteUiActionStateLiveData.UiState.Icon

            Assert.assertEquals(R.drawable.ic_favorite_unchecked, uiStateSuccess.icon)
        }

    @Test
    fun `should notify favorite_uiState with filled favorite icon when current icon is unchecked`() =
        runTest {
            // Arrange
            whenever(addFavoriteUseCase.invoke(any()))
                .thenReturn(
                    flowOf(
                        ResultStatus.Success(Unit)
                    )
                )
            // Action
            detailViewModel.run {
                favorite.currentFavoriteIcon = R.drawable.ic_favorite_unchecked
                favorite.update(
                    DetailViewArg(
                        character.id,
                        character.name,
                        character.imageUrl,
                    )
                )
            }
            // Assert
            verify(favoriteUiStateObserver).onChanged(
                isA<FavoriteUiActionStateLiveData.UiState.Icon>()
            )
            val uiStateSuccess =
                detailViewModel.favorite.state.value as FavoriteUiActionStateLiveData.UiState.Icon

            Assert.assertEquals(R.drawable.ic_favorite_checked, uiStateSuccess.icon)
        }

    @Test
    fun `should call remove and notify favorite_uiState with filled favorite icon when current icon is checked`() =
        runTest {
            // Arrange
            whenever(removeFavoriteUseCase.invoke(any()))
                .thenReturn(
                    flowOf(
                        ResultStatus.Success(Unit)
                    )
                )
            // Action
            detailViewModel.run {
                favorite.currentFavoriteIcon = R.drawable.ic_favorite_checked
                favorite.update(
                    DetailViewArg(
                        character.id,
                        character.name,
                        character.imageUrl,
                    )
                )
            }
            // Assert
            verify(favoriteUiStateObserver).onChanged(
                isA<FavoriteUiActionStateLiveData.UiState.Icon>()
            )
            val uiStateSuccess =
                detailViewModel.favorite.state.value as FavoriteUiActionStateLiveData.UiState.Icon

            Assert.assertEquals(R.drawable.ic_favorite_unchecked, uiStateSuccess.icon)
        }
}
