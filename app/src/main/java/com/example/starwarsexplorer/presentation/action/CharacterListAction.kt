package com.example.starwarsexplorer.presentation.action

sealed class CharacterListAction {
    data class NavigateToDetails(val id: String) : CharacterListAction()
}
