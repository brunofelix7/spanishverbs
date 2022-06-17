package me.brunofelix.spanishverbs.ui.home

sealed class HomeUiState {
    object Loading: HomeUiState()
    object Finished: HomeUiState()
}