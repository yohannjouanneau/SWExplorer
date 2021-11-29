package com.example.starwarsexplorer.data.remote.client

import com.example.starwarsexplorer.data.remote.response.PeopleListResponse
import com.example.starwarsexplorer.domain.model.CharacterModel
import retrofit2.http.GET
import retrofit2.http.Query

interface SWApiService {

    @GET("people")
    suspend fun fetchPeoples(
        @Query("page") pageNumber: Int,
        @Query("limit") limit: Int = 10
    ) : PeopleListResponse
}