package com.android4you.space.presentation.launches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android4you.space.databinding.FragmentLaunchesBinding
import com.android4you.space.presentation.launches.adapter.LaunchesPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchesFragment : Fragment() {

    private var _binding: FragmentLaunchesBinding? = null

    val binding get() = _binding!!

    private val tabs = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLaunchesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.toolbar?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        tabs.add("All")
        tabs.add("Past")
        tabs.add("Upcoming")
        setupViewPager()
        setupTabLayout()
    }

    private fun setupTabLayout() {
        TabLayoutMediator(
            _binding!!.tabLayout, _binding!!.viewPager
        ) { tab, position -> tab.text = tabs[position] }.attach()
    }

    private fun setupViewPager() {
        val adapter = LaunchesPagerAdapter(requireActivity(), 3)
        _binding!!.viewPager.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
