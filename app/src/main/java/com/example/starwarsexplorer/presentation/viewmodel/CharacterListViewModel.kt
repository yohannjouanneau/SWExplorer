package com.example.starwarsexplorer.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.starwarsexplorer.domain.paging.StarWarsPeopleSource
import com.example.starwarsexplorer.presentation.action.CharacterListAction
import com.example.starwarsexplorer.presentation.action.CharacterListViewAction
import com.example.starwarsexplorer.presentation.base.ErrorState
import com.example.starwarsexplorer.presentation.base.ScreenState
import com.example.starwarsexplorer.presentation.base.ScreenViewModel
import com.example.starwarsexplorer.presentation.state.CharacterListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val starWarsPeopleSource: StarWarsPeopleSource
): ScreenViewModel<CharacterListViewState, CharacterListViewAction, CharacterListAction>(
    initialScreenState = ScreenState(
        isLoading = true,
    ),
){
    init {
        viewModelScope.launch {
            postViewState {
                CharacterListViewState(
                    characterFlow = peoplePageFlow()
                )
            }
        }
    }

    private fun peoplePageFlow() =
        Pager(PagingConfig(pageSize = PAGE_SIZE)) {starWarsPeopleSource}.flow
            .catch { exception ->
                postScreenState { screenState ->
                    screenState.copy(
                        errorState = ErrorState(
                            throwable = exception
                        )
                    )
                }
            }

    override fun handleViewAction(viewAction: CharacterListViewAction) {
        when (viewAction) {
            is CharacterListViewAction.OnCharacterPressed -> {
                posAction(CharacterListAction.NavigateToDetails(viewAction.id))
            }
        }
    }

    companion object {
        private const val PAGE_SIZE = 10
    }
}