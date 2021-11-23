package com.example.starwarsexplorer.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class ScreenViewModel<ViewState, ViewAction, Action>(
    initialScreenState: ScreenState<ViewState>
) : ViewModel(), StateManager<ViewState, ViewAction> {

    private val mutableStateFlow = MutableStateFlow(initialScreenState)
    override val stateFlow: StateFlow<ScreenState<ViewState>>
        get() = mutableStateFlow

    private val mutableActionFlow = MutableSharedFlow<Action>()
    val actionFlow
        get() = mutableActionFlow


    suspend fun postScreenState(mutate: (ScreenState<ViewState>) -> ScreenState<ViewState>) {
        mutableStateFlow.emit(mutate(mutableStateFlow.value))
    }

    suspend fun postViewState(mutate: (ViewState?) -> ViewState?) {
        mutableStateFlow.emit(
            mutableStateFlow.value.copy(
                isLoading = false,
                viewState = mutate(mutableStateFlow.value.viewState)
            )
        )
    }

    fun posAction(action: Action) {
        viewModelScope.launch {
            mutableActionFlow.emit(action)
        }
    }
}