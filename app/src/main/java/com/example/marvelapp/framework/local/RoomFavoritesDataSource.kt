package com.example.marvelapp.framework.local

import com.example.core.data.repositories.FavoritesLocalDataSource
import com.example.core.domain.model.Character
import com.example.marvelapp.framework.db.dao.FavoriteDao
import com.example.marvelapp.framework.db.entity.FavoriteEntity
import com.example.marvelapp.framework.db.entity.toCharactersModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomFavoritesDataSource @Inject constructor(
    private val dao: FavoriteDao
) : FavoritesLocalDataSource {

    override fun getAll(): Flow<List<Character>> {
        return dao.loadFavorites().map { it.toCharactersModel() }
    }

    override suspend fun isFavorite(characterId: Int): Boolean {
        return dao.hasFavorite(characterId) != null
    }

    override suspend fun save(character: Character) {
        dao.insertFavorite(character.toFavoriteEntity())
    }

    override suspend fun delete(character: Character) {
        dao.deleteFavorite(character.toFavoriteEntity())

    }

    private fun Character.toFavoriteEntity() = FavoriteEntity(this.id, this.name, this.imageUrl)
}