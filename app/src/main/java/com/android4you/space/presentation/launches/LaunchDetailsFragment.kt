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
import androidx.navigation.fragment.findNavController
import com.android4you.space.databinding.FragmentLaunchDetailsBinding
import com.android4you.space.domain.model.LaunchModel
import com.android4you.space.domain.model.launchdetails.BaseDetailsUIModel
import com.android4you.space.presentation.launches.adapter.LaunchDetailsAdapter
import com.android4you.space.presentation.launches.viewmodel.LaunchDetailFlowsViewState
import com.android4you.space.presentation.launches.viewmodel.LaunchDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

sealed class Status {
    object Error : Status()
    class Success : Status()
}

@AndroidEntryPoint
class LaunchDetailsFragment : Fragment() {

    private var _binding: FragmentLaunchDetailsBinding? = null

    val binding get() = _binding!!

    private val launchDetailsViewModel: LaunchDetailsViewModel by viewModels()

    private var adapter: LaunchDetailsAdapter? = null

    private var launch: LaunchModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLaunchDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launch = arguments?.getParcelable("launch")
        launch?.let {
            launchDetailsViewModel.fetchLaunchDetails(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarHome.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.apply {
            launches = launch
        }
        adapter = LaunchDetailsAdapter()
        _binding?.recyclerList?.adapter = adapter
        observe()
    }

    private fun observe() {
        launchDetailsViewModel.viewStateFlow
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleStateChange(state.userList) }
            .launchIn(lifecycleScope)
        launchDetailsViewModel.viewStateTestFlow
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleTestStateChange(state) }
            .launchIn(lifecycleScope)
    }
    private fun handleTestStateChange(state: LaunchDetailFlowsViewState) {
        state.model?.let {
            adapter?.setUIOneList(it)
        }
    }
    private fun handleStateChange(state: List<BaseDetailsUIModel>) {
        adapter?.setUIList(state)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
