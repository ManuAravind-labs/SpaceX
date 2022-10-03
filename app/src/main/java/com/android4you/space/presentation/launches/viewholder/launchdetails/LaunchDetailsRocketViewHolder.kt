package com.android4you.space.presentation.launches.viewholder.launchdetails

import androidx.recyclerview.widget.LinearLayoutManager
import com.android4you.space.databinding.LayoutLaunchDetailsRocketInfoBinding
import com.android4you.space.domain.model.launchdetails.LaunchDetailsRocketUIModel
import com.android4you.space.domain.model.rockets.Diameter
import com.android4you.space.domain.model.rockets.Height
import com.android4you.space.domain.model.rockets.Mass
import com.android4you.space.domain.model.rockets.PayloadWeight
import com.android4you.space.presentation.launches.adapter.LaunchesBannerAdapter
import com.android4you.space.presentation.rockets.adapter.PayloadWeightAdapter

class LaunchDetailsRocketViewHolder(private val binding: LayoutLaunchDetailsRocketInfoBinding) :
    BaseLaunchDetailsViewHolder<LaunchDetailsRocketUIModel>(binding) {
    override fun bind(item: LaunchDetailsRocketUIModel) = with(binding) {
        this.rockets = item.rocketModel
        executePendingBindings()
        item.rocketModel.flickr_images?.let {
            setFlickerImages(it)
        }
        item.rocketModel.payload_weights?.let {
            setPayloadWeight(it)
        }

        setHeight(item.rocketModel.height)
        setDiameter(item.rocketModel.diameter)
        setMass(item.rocketModel.mass)
    }

    private fun setPayloadWeight(list: List<PayloadWeight>) {
        val payloadWeightAdapter = PayloadWeightAdapter()
        binding.payloadWeightRecyclerview.adapter = payloadWeightAdapter
        binding.payloadWeightRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        payloadWeightAdapter.submitList(list)
    }

    private fun setFlickerImages(images: List<String>) {
        val adapter = LaunchesBannerAdapter()
        binding.flickerImages.adapter = adapter
        binding.flickerImages.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter.submitList(images)
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
}
