package com.android4you.space.presentation.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android4you.space.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    // The type of binding class will change from fragment to fragment
    private var _binding: FragmentAboutBinding? = null

    val binding get() = _binding!! // Helper Property

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // As the binding class will change, binding inflate method will also change from fragment to fragment
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}