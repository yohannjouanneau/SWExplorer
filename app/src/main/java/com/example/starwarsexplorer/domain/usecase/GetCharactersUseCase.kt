package com.example.starwarsexplorer.domain.usecase

import com.example.starwarsexplorer.domain.model.CharacterModel
import com.example.starwarsexplorer.domain.repository.StarWarsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: StarWarsRepository
) {

    suspend fun execute(): Flow<List<CharacterModel>> {
        return repository.getCharacterList()
    }
}
