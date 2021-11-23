package com.example.starwarsexplorer.data.remote.client

import com.example.starwarsexplorer.data.remote.response.PeopleListResponse
import com.example.starwarsexplorer.domain.model.CharacterModel
import retrofit2.http.GET

interface SWApiService {

    @GET("people")
    suspend fun fetchPeoples() : PeopleListResponse
}