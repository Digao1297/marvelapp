package com.example.core.usecase

import androidx.paging.PagingConfig

data class GetCharactersParams(
    val query: String,
    val pagingConfig: PagingConfig
)
