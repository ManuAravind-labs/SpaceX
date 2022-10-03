package com.android4you.space.presentation.launches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android4you.space.databinding.FragmentPagerItemBinding

class LaunchesNextFragment : Fragment() {

    private var _binding: FragmentPagerItemBinding? = null

    val binding get() = _binding!! // Helper Property

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPagerItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
