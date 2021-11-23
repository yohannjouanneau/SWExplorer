package com.example.starwarsexplorer.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.starwarsexplorer.domain.usecase.GetCharactersUseCase
import com.example.starwarsexplorer.presentation.action.CharacterListAction
import com.example.starwarsexplorer.presentation.action.CharacterListViewAction
import com.example.starwarsexplorer.presentation.base.ErrorState
import com.example.starwarsexplorer.presentation.base.ScreenState
import com.example.starwarsexplorer.presentation.base.ScreenViewModel
import com.example.starwarsexplorer.presentation.state.CharacterListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    getCharactersUseCase: GetCharactersUseCase
): ScreenViewModel<CharacterListViewState, CharacterListViewAction, CharacterListAction>(
    initialScreenState = ScreenState(
        isLoading = true,
    ),
){

    init {
        viewModelScope.launch {
            getCharactersUseCase.execute()
                .catch { exception ->
                    postScreenState { screenState ->
                        screenState.copy(
                            errorState = ErrorState(
                                throwable = exception
                            )
                        )
                    }
                }
                .collect { characters ->
                    postViewState {
                        CharacterListViewState(
                            characters = characters
                        )
                    }
                }
        }
    }

    override fun handleViewAction(viewAction: CharacterListViewAction) {
        when (viewAction) {
            is CharacterListViewAction.OnCharacterPressed -> {
                posAction(CharacterListAction.NavigateToDetails(viewAction.id))
            }
        }
    }
}