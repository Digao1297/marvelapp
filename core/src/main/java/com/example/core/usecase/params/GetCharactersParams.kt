package com.example.core.usecase.params

import androidx.paging.PagingConfig

data class GetCharactersParams(
    val query: String,
    val pagingConfig: PagingConfig
)
