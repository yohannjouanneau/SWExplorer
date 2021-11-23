package com.example.starwarsexplorer.presentation.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.starwarsexplorer.domain.model.CharacterModel
import com.example.starwarsexplorer.presentation.action.CharacterListViewAction
import com.example.starwarsexplorer.presentation.state.CharacterListViewState

@Composable
fun CharacterListScreen(
    viewState: CharacterListViewState,
    viewActionHandler: (CharacterListViewAction) -> Unit
) {
    LazyColumn(content = {
        items(
            items = viewState.characters,
            key = { item -> item.name },
            itemContent = {
                CharacterRow(
                    modifier = Modifier.clickable {
                        viewActionHandler(CharacterListViewAction.OnCharacterPressed(
                            id = it.uid
                        ))
                    },
                    characterModel = it
                )
                Divider()
            }
        )
    })
}

@Composable
private fun CharacterRow(characterModel: CharacterModel, modifier: Modifier) {
    Text(
        text = characterModel.name,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}