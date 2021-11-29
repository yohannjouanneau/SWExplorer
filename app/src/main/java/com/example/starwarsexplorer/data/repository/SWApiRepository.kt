package com.example.starwarsexplorer.data.repository

import com.example.starwarsexplorer.data.remote.client.StarWarsApiClient
import com.example.starwarsexplorer.domain.model.CharacterModel
import com.example.starwarsexplorer.domain.repository.StarWarsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ExperimentalSerializationApi
class SWApiRepository @Inject constructor(
    private val apiClient: StarWarsApiClient
): StarWarsRepository {

    override suspend fun getCharacterList(
        pageNumber: Int,
        pageSize: Int
    ): List<CharacterModel> {
        return apiClient.getCharacterList(
            pageNumber = pageNumber,
            pageSize = pageSize
        )
    }
}