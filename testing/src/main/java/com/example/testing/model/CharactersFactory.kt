package com.example.testing.model

import com.example.core.domain.model.Character

class CharactersFactory {

    fun create(hero: Hero): Character =
        when (hero) {
            Hero.ThreeDMan -> Character(
                id = 1011334,
                name = "3-D Man",
                imageUrl = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            )
            Hero.ABom -> Character(
                id = 1017100,
                name = "A-Bomb (HAS)",
                imageUrl = "https://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg"
            )
        }


    sealed class Hero {
        object ThreeDMan : Hero()
        object ABom : Hero()
    }
}