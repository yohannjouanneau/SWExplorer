package com.example.starwarsexplorer.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.lifecycle.lifecycleScope
import com.example.starwarsexplorer.presentation.action.CharacterListAction
import com.example.starwarsexplorer.presentation.base.RenderScreen
import com.example.starwarsexplorer.presentation.ui.screen.CharacterListScreen
import com.example.starwarsexplorer.presentation.ui.theme.StarWarsExplorerTheme
import com.example.starwarsexplorer.presentation.viewmodel.CharacterListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CharacterListActivity : ComponentActivity() {

    private val viewModel by viewModels<CharacterListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenCreated {
            viewModel.actionFlow.collect { action ->
                handleAction(action)
            }
        }

        setContent {
            StarWarsExplorerTheme {
                Surface(color = MaterialTheme.colors.background) {
                    RenderScreen(
                        stateManager = viewModel,
                    ) { viewState, viewActionHandler ->
                        CharacterListScreen(viewState, viewActionHandler)
                    }
                }
            }
        }
    }

    private fun handleAction(action: CharacterListAction) {
        when (action) {
            is CharacterListAction.NavigateToDetails -> {
                Toast.makeText(this, "Character ${action.id} tapped", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
