package com.example.marvelapp.presentation.characters.adapters

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class CharactersRefreshStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<CharactersLoadRefreshStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CharactersLoadRefreshStateViewHolder =
        CharactersLoadRefreshStateViewHolder.create(parent, retry)

    override fun onBindViewHolder(
        holder: CharactersLoadRefreshStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)

}