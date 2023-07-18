package com.example.testing.model

import com.example.core.domain.model.Comic

class ComicsFactory {

    fun create(comic: FakeComic): Comic =
        when (comic) {
            FakeComic.FakeComic1 -> Comic(
                id = 2211506,
                imageUrl = "http://fakecomicurl.jpg"
            )
        }

    sealed class FakeComic {
        object FakeComic1 : FakeComic()
    }
}