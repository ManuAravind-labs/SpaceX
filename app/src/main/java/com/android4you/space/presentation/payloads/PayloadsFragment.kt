package com.android4you.space.presentation.payloads

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
import com.android4you.space.databinding.FragmentPayloadsBinding
import com.android4you.space.presentation.PayloadViewType
import com.android4you.space.presentation.payloads.adapter.PayLoadsAdapter
import com.android4you.space.presentation.payloads.viewmodel.PayloadsViewModel
import com.android4you.space.presentation.payloads.viewmodel.PayloadsViewState
import com.android4you.space.utils.makeGone
import com.android4you.space.utils.makeVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PayloadsFragment : Fragment() {

    private val payloadViewModel: PayloadsViewModel by viewModels()

    private var _binding: FragmentPayloadsBinding? = null

    val binding get() = _binding!!

    private lateinit var payLoadsAdapter: PayLoadsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPayloadsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.toolbarHome?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        payLoadsAdapter = PayLoadsAdapter(PayloadViewType.FROM_LIST)
        _binding?.payloadList?.adapter = payLoadsAdapter
        observe()
    }

    private fun observe() {
        payloadViewModel.payloadsStateflow
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleStateChange(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleStateChange(state: PayloadsViewState) {
        when (state) {
            is PayloadsViewState.Empty -> Unit
            is PayloadsViewState.Error -> {
                _binding?.apply {
                    errorTextview.makeVisible()
                    errorTextview.text = state.message
                    progressBar.makeGone()
                }
            }
            is PayloadsViewState.Success -> {
                payLoadsAdapter.submitList(state.payloads)
                _binding?.apply {
                    progressBar.makeGone()
                    errorTextview.makeGone()
                }
            }
            is PayloadsViewState.Loading -> {
                if (state.isLoading) _binding?.progressBar?.makeVisible() else _binding?.progressBar?.makeGone()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
