package com.android4you.space.presentation.rockets

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android4you.space.databinding.FragmentRocketsBinding
import com.android4you.space.domain.model.rockets.Diameter
import com.android4you.space.domain.model.rockets.Height
import com.android4you.space.domain.model.rockets.Mass
import com.android4you.space.domain.model.rockets.RocketModel
import com.android4you.space.presentation.launches.adapter.LaunchesBannerAdapter
import java.util.*

class RocketsFragment : Fragment() {

    private var _binding: FragmentRocketsBinding? = null

    val binding get() = _binding!!
    private var currentPage = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRocketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rocketmodel = arguments?.getParcelable<RocketModel>("rocket")
        _binding?.apply {
            rocket = rocketmodel
        }
        _binding?.backButton?.setOnClickListener {
            findNavController().popBackStack()
        }
        val adapter = LaunchesBannerAdapter()
        _binding?.viewPager?.adapter = adapter

        rocketmodel?.let {
            adapter.submitList(it.flickr_images)
            setHeight(it.height)
            setDiameter(it.diameter)
            setMass(it.mass)
            val handler = Handler()
            val update = Runnable {
                if (currentPage == it.flickr_images?.size) {
                    currentPage = 0
                }
                _binding?.viewPager?.setCurrentItem(currentPage++, true)
            }

            Timer().schedule(
                object : TimerTask() {
                    // task to be scheduled
                    override fun run() {
                        handler.post(update)
                    }
                },
                3500, 3500
            )
        }
    }

    private fun setHeight(model: Height?) {
        val str = StringBuilder("")
        model?.let { it ->
            it.feet?.let { value ->
                str.append("$value Ft")
            }
            it.meters?.let { value ->
                str.append("\n$value m")
            }
        }
        binding.heightValue.text = str
    }

    private fun setDiameter(model: Diameter?) {
        val str = StringBuilder("")
        model?.let { it ->
            it.feet?.let { value ->
                str.append("$value Ft")
            }
            it.meters?.let { value ->
                str.append("\n$value m")
            }
        }
        binding.diameterValue.text = str
    }

    private fun setMass(model: Mass?) {
        val str = StringBuilder("")
        model?.let { it ->
            it.kg?.let { value ->
                str.append("$value kg")
            }
            it.lb?.let { value ->
                str.append("\n$value lb")
            }
        }
        binding.weightValue.text = str
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
