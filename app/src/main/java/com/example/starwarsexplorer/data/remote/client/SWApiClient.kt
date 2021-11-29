package com.example.starwarsexplorer.data.remote.client

import com.example.starwarsexplorer.domain.model.CharacterModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@ExperimentalSerializationApi
class SWApiClient : StarWarsApiClient {

    private val contentType = "application/json".toMediaType()
    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.swapi.tech/api/")
        .client(OkHttpClient())
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()

    private val client = retrofit.create(SWApiService::class.java)

    override suspend fun getCharacterList(
        pageNumber: Int,
        pageSize: Int
    ): List<CharacterModel> {
        return client.fetchPeoples(
            pageNumber = pageNumber,
            limit = pageSize
        ).results
            .map { people ->
                CharacterModel(
                    uid = people.uid,
                    name = people.name
                )
            }
    }
}