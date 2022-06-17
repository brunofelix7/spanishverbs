package me.brunofelix.spanishverbs.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineDispatcher
import me.brunofelix.spanishverbs.data.VerbRepository

class HomeViewModelFactory constructor(
    private val repository: VerbRepository,
    private val dispatcher: CoroutineDispatcher,
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(repository, dispatcher, context) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}