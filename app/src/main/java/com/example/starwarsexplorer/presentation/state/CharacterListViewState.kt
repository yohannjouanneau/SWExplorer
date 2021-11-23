package com.example.starwarsexplorer.presentation.state

import com.example.starwarsexplorer.domain.model.CharacterModel

data class CharacterListViewState(
    val characters: List<CharacterModel> = emptyList()
)
