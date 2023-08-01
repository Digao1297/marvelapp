package com.example.marvelapp.presentation.detail

import androidx.lifecycle.ViewModel
import com.example.core.usecase.AddFavoriteUseCase
import com.example.core.usecase.GetCharacterCategoryUseCase
import com.example.core.usecase.base.CoroutinesDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCharacterCategoryUseCase: GetCharacterCategoryUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    coroutineDispatcher: CoroutinesDispatchers,
) : ViewModel() {

    val categories = UiActionStateLiveData(
        coroutineDispatcher.main(),
        getCharacterCategoryUseCase,
    )

    val favorite = FavoriteUiActionStateLiveData(
        coroutineDispatcher.main(),
        addFavoriteUseCase,
    )

    init {
        favorite.setDefault()
    }
}