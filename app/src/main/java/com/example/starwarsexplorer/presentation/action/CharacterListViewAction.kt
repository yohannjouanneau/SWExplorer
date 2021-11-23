package com.example.starwarsexplorer.presentation.action

sealed class CharacterListViewAction {
    data class OnCharacterPressed(val id: String) : CharacterListViewAction()
}
