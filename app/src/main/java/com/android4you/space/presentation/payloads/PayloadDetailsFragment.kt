package com.android4you.space.presentation.payloads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android4you.space.databinding.FragmentPayloadDetailsBinding
import com.android4you.space.domain.model.LaunchModel
import com.android4you.space.domain.model.payloads.PayloadsModel

class PayloadDetailsFragment : Fragment() {

    private var _binding: FragmentPayloadDetailsBinding? = null

    val binding get() = _binding!!

    private var payload: PayloadsModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPayloadDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        payload = arguments?.getParcelable("payload")

        _binding?.apply {
            payloads = payload
        }
//        _binding?.toolbarHome?.setNavigationOnClickListener {
//            findNavController().popBackStack()
//        }
    }
}
