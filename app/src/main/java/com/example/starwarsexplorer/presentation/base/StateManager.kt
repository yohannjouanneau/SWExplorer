package com.example.starwarsexplorer.presentation.base

import kotlinx.coroutines.flow.StateFlow

interface StateManager<ViewState, ViewAction> {
    val stateFlow: StateFlow<ScreenState<ViewState>>
    fun handleViewAction(viewAction: ViewAction)
}
