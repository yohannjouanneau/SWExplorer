package com.example.starwarsexplorer.domain.repository

import com.example.starwarsexplorer.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface StarWarsRepository {
    suspend fun getCharacterList(): Flow<List<CharacterModel>>
}
