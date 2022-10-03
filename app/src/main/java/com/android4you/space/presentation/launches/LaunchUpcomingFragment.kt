package com.android4you.space.presentation.launches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android4you.space.databinding.FragmentPagerItemBinding
import com.android4you.space.presentation.launches.adapter.LaunchesAdapter
import com.android4you.space.presentation.launches.viewmodel.LaunchViewState
import com.android4you.space.presentation.launches.viewmodel.LaunchesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LaunchUpcomingFragment : Fragment() {

    private var _binding: FragmentPagerItemBinding? = null

    val binding get() = _binding!!

    private val launchesViewModel: LaunchesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPagerItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchesViewModel.fetchAllUpcomingLaunches()
        observe()
    }

    private fun observe() {
        launchesViewModel.stateflow
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleStateChange(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleStateChange(state: LaunchViewState) {
        when (state) {
            is LaunchViewState.Empty -> Unit
            is LaunchViewState.Error -> {
                _binding?.viewFlipper?.displayedChild = 2
            }
            is LaunchViewState.Success -> {
                val listAdapter = LaunchesAdapter(true)
                _binding?.launchesList?.adapter = listAdapter
                _binding?.launchesList?.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                listAdapter.submitList(state.list)
                _binding?.viewFlipper?.displayedChild = 1
            }
            is LaunchViewState.Loading -> {
                _binding?.viewFlipper?.displayedChild = 0
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
