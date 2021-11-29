package com.example.starwarsexplorer.data.remote.client

import com.example.starwarsexplorer.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface StarWarsApiClient {
    suspend fun getCharacterList(
        pageNumber: Int,
        pageSize: Int
    ) : List<CharacterModel>
}
