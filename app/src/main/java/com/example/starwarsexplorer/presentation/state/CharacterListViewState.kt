package com.example.starwarsexplorer.presentation.state

import androidx.paging.PagingData
import com.example.starwarsexplorer.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

data class CharacterListViewState(
    val characterFlow: Flow<PagingData<CharacterModel>>
)