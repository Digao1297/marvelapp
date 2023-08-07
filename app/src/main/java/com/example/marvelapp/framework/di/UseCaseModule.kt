package com.example.marvelapp.framework.di

import com.example.core.usecase.AddFavoriteUseCase
import com.example.core.usecase.AddFavoriteUseCaseImpl
import com.example.core.usecase.CheckFavoriteUseCase
import com.example.core.usecase.CheckFavoriteUseCaseImpl
import com.example.core.usecase.GetCharacterCategoryUseCase
import com.example.core.usecase.GetCharacterCategoryUseCaseImpl
import com.example.core.usecase.GetCharactersUseCase
import com.example.core.usecase.GetCharactersUseCaseImpl
import com.example.core.usecase.GetFavoritesUseCase
import com.example.core.usecase.GetFavoritesUseCaseImpl
import com.example.core.usecase.RemoveFavoriteUseCase
import com.example.core.usecase.RemoveFavoriteUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindCharactersUseCase(useCase: GetCharactersUseCaseImpl): GetCharactersUseCase

    @Binds
    fun bindGetCharacterCategoryUseCase(useCase: GetCharacterCategoryUseCaseImpl): GetCharacterCategoryUseCase

    @Binds
    fun bindAddFavoriteUseCase(useCase: AddFavoriteUseCaseImpl): AddFavoriteUseCase

    @Binds
    fun bindCheckFavoriteUseCase(useCase: CheckFavoriteUseCaseImpl): CheckFavoriteUseCase

    @Binds
    fun bindRemoveFavoriteUseCase(useCase: RemoveFavoriteUseCaseImpl): RemoveFavoriteUseCase

    @Binds
    fun bindFavoritesUseCase(useCase: GetFavoritesUseCaseImpl): GetFavoritesUseCase
}