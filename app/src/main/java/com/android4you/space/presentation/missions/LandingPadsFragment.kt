package com.android4you.space.presentation.missions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android4you.space.databinding.FragmentLaunchPadsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingPadsFragment : Fragment() {

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
