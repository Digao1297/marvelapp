package com.example.marvelapp.framework.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.data.DbConstants
import com.example.core.domain.model.Character

@Entity(tableName = DbConstants.CHARACTER_TABLE_NAME)
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val autoId: Int = 0,
    @ColumnInfo(DbConstants.CHARACTER_COLUMN_INFO_ID)
    val id: Int,
    @ColumnInfo(DbConstants.CHARACTER_COLUMN_INFO_NAME)
    val name: String,
    @ColumnInfo(DbConstants.CHARACTER_COLUMN_INFO_IMAGE_URL)
    val imageUrl: String
)

fun CharacterEntity.toCharacterModel() =
    Character(id, name, imageUrl)