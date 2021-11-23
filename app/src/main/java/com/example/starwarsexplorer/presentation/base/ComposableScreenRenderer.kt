package com.example.starwarsexplorer.presentation.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun <ViewState, ViewAction> RenderScreen(
    stateManager: StateManager<ViewState, ViewAction>,
    render: @Composable (ViewState, (ViewAction) -> Unit) -> Unit
) {
    val screenState by stateManager.stateFlow.collectAsState()

    when {
        screenState.isLoading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
        screenState.errorState != null -> {
            Text(screenState.errorState?.errorMessage ?: "Error !")
        }
        else -> {
            screenState.viewState?.let {
                render(it, stateManager::handleViewAction)
            }
        }
    }
}
