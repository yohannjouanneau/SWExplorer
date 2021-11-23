package com.example.starwarsexplorer.di

import com.example.starwarsexplorer.data.remote.client.SWApiClient
import com.example.starwarsexplorer.data.remote.client.StarWarsApiClient
import com.example.starwarsexplorer.data.repository.SWApiRepository
import com.example.starwarsexplorer.domain.repository.StarWarsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun provideStarWarsRepository(
        apiClient: StarWarsApiClient
    ): StarWarsRepository {
        return SWApiRepository(apiClient)
    }

    @Provides
    fun provideApiClient(): StarWarsApiClient {
        return SWApiClient()
    }
}
