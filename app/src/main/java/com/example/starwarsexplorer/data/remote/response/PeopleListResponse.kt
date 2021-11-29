package com.example.starwarsexplorer.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class PeopleListResponse(
    val next: String?,
    val results: List<People>
)

@Serializable
data class People(
    val uid: String,
    val name: String,
    val url: String
)
