package me.brunofelix.spanishverbs.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.preference.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.brunofelix.spanishverbs.R
import me.brunofelix.spanishverbs.data.AppDatabase
import me.brunofelix.spanishverbs.data.Verb
import me.brunofelix.spanishverbs.data.VerbRepositoryImpl
import me.brunofelix.spanishverbs.databinding.FragmentHomeBinding
import me.brunofelix.spanishverbs.extensions.toast
import me.brunofelix.spanishverbs.ui.BaseFragment

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
), HomeClickListener {

    private lateinit var adapter: HomeAdapter

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initObjects()
        observeData()
    }

    override fun getFragmentView() = R.layout.fragment_home

    private fun initUI() {

    }

    private fun initObjects() {
        fillDatabase()
    }

    private fun initAdapter() {
        adapter = HomeAdapter()
        adapter.listener = this
        adapter.context = requireContext()

        binding.rvVerbs.adapter = adapter.withLoadStateHeaderAndFooter(
            header = ItemLoadStateAdapter { adapter.retry() },
            footer = ItemLoadStateAdapter { adapter.retry() }
        )

        adapter.addLoadStateListener { loadState ->
            binding.rvVerbs.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.btnRetry.isVisible = loadState.source.refresh is LoadState.Error

            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                activity?.toast("\uD83D\uDE28 Oops! ${it.error}")
            }
        }
    }

    private fun fillDatabase() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())

        if (prefs.getBoolean(getString(R.string.pref_is_db_empty), true)) {
            prefs.edit().apply {
                putBoolean(getString(R.string.pref_is_db_empty), false)
                apply()
            }
            viewModel.insertVerb()
        } else {
            collectData()
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.liveData.observe(viewLifecycleOwner) { uiState ->
                when (uiState) {
                    is HomeUiState.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is HomeUiState.Finished -> {
                        binding.progressBar.isVisible = false
                        collectData()
                    }
                }
            }
        }
    }

    private fun collectData() {
        initAdapter()

        lifecycleScope.launch {
            viewModel.listVerbs().collect {
                adapter.submitData(it)
            }
        }
    }

    override fun onItemClick(verb: Verb) {

    }

    override fun onFavoriteClick(verb: Verb) {

    }
}