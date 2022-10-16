package com.android4you.space.presentation.missions

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
import com.android4you.space.R
import com.android4you.space.databinding.FragmentLaunchPadsBinding
import com.android4you.space.presentation.missions.adapter.LaunchPadsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LaunchPadsFragment : Fragment() {

    private var _binding: FragmentLaunchPadsBinding? = null

    val binding get() = _binding!!

    private val launchesViewModel: MissionsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLaunchPadsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchesViewModel.fetchAllLaunchPads()
        observe()
    }
    private fun observe() {
        launchesViewModel.stateflow
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleStateChange(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleStateChange(state: LaunchPadsViewState) {
        when (state) {
            is LaunchPadsViewState.Empty -> Unit
            is LaunchPadsViewState.NoNetworkError -> {
                _binding?.viewFlipper?.displayedChild = 2
                _binding?.errorTextview?.text = getString(R.string.no_internet)
            }
            is LaunchPadsViewState.NotFoundError -> {
                _binding?.viewFlipper?.displayedChild = 2
                _binding?.errorTextview?.text = getString(R.string.no_result)
            }
            is LaunchPadsViewState.UnknownError -> {
                _binding?.viewFlipper?.displayedChild = 2
                _binding?.errorTextview?.text = getString(R.string.server_error)
            }
            is LaunchPadsViewState.Success -> {
                val listAdapter = LaunchPadsAdapter()
                _binding?.launchesList?.adapter = listAdapter
                _binding?.launchesList?.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                listAdapter.submitList(state.launchPadsList)
                _binding?.viewFlipper?.displayedChild = 1
            }
            is LaunchPadsViewState.Loading -> {
                _binding?.viewFlipper?.displayedChild = 0
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
