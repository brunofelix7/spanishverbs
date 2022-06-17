package me.brunofelix.spanishverbs.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.brunofelix.spanishverbs.data.Verb
import me.brunofelix.spanishverbs.data.VerbRepository
import me.brunofelix.spanishverbs.data.json.ConjugationJson
import me.brunofelix.spanishverbs.data.json.RootJson
import me.brunofelix.spanishverbs.extensions.getJsonFromAssets
import me.brunofelix.spanishverbs.extensions.listAZ

class HomeViewModel constructor(
    private val repository: VerbRepository,
    private val dispatcher: CoroutineDispatcher,
    private val context: Context
) : ViewModel() {

    private val _liveData = MutableLiveData<HomeUiState>()
    val liveData: LiveData<HomeUiState> get() = _liveData

    fun insertVerb() {
        _liveData.value = HomeUiState.Loading

        viewModelScope.launch(dispatcher) {
            val gson = Gson()

            for (letter in listAZ()) {
                val json = context.getJsonFromAssets("${letter}_verbos.json")

                if (json.isNotEmpty()) {
                    val verbsList = gson.fromJson(json, Array<RootJson>::class.java).asList()

                    for (item in verbsList) {
                        val name = item.verbo
                        val gerund = item.progresivo.presente.yo.replace("estoy ", "")
                        val conjugation = ConjugationJson(
                            item.perfecto, item.imperativo, item.indicativo,
                            item.progresivo, item.subjuntivo, item.perfectoSubjuntivo)
                        val verb = Verb(0, name, gerund, gson.toJson(conjugation))

                        repository.insert(verb)
                    }
                }
            }

            withContext(Dispatchers.Main) {
                _liveData.value = HomeUiState.Finished
            }
        }
    }

    fun listVerbs(): Flow<PagingData<Verb>> {
        return Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            repository.findAll()
        }.flow
    }
}