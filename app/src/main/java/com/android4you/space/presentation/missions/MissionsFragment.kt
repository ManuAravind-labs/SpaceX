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
import androidx.navigation.fragment.findNavController
import com.android4you.space.databinding.FragmentMissionBinding
import com.android4you.space.presentation.launches.adapter.LaunchesPagerAdapter
import com.android4you.space.presentation.missions.adapter.PadsPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MissionsFragment : Fragment() {

    private var _binding: FragmentMissionBinding? = null

    val binding get() = _binding!!

    private val tabs = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMissionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.toolbarHome?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        tabs.add("Launch Pads")
        tabs.add("Landing Pads")
        setupViewPager()
        setupTabLayout()
    }

    private fun setupTabLayout() {
        TabLayoutMediator(
            _binding!!.tabLayout, _binding!!.viewPager
        ) { tab, position -> tab.text = tabs[position] }.attach()
    }

    private fun setupViewPager() {
        val adapter = PadsPagerAdapter(requireActivity(), 2)
        _binding!!.viewPager.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
