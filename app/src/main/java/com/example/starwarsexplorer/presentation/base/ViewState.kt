package com.example.starwarsexplorer.presentation.base

data class ScreenState<State>(
    val isLoading: Boolean = false,
    val errorState: ErrorState? = null,
    val viewState: State? = null,
)

data class ErrorState(
    val throwable: Throwable,
    var errorMessage: String? = null
)
