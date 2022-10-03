package com.android4you.space.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.android4you.space.databinding.FragmentHomeBinding
import com.android4you.space.presentation.home.adapter.HomeListAdapter
import com.android4you.space.presentation.home.viewmodel.HomeViewModel
import com.android4you.space.presentation.home.viewmodel.HomeViewState
import com.android4you.space.utils.makeGone
import com.android4you.space.utils.makeVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    private var adapter: HomeListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = HomeListAdapter()
        _binding?.recyclerList?.adapter = adapter
        observe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        homeViewModel.homeViewStateFlow
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleStateChange(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleStateChange(state: HomeViewState) {
        when (state) {
            is HomeViewState.Empty -> Unit
            is HomeViewState.Error -> {
                _binding?.apply {
                    progressBar.makeGone()
                    errorTextview.makeVisible()
                    errorTextview.text = state.message
                }
            }
            is HomeViewState.Success -> {
                state.list?.let {
                    if (it.size > 1) {
                        adapter?.setUIList(state.list)
                    } else if (it.size == 1) {
                        adapter?.setUIOneList(state.list.first())
                    } else {
                    }
                }
                _binding?.apply {
                    progressBar.makeGone()
                    errorTextview.makeGone()
                }
            }
            is HomeViewState.Loading -> {
                _binding?.apply {
                    progressBar.makeVisible()
                    errorTextview.makeGone()
                }
            }
        }
    }
}
