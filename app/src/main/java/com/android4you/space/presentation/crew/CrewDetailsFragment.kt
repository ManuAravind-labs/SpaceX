package com.android4you.space.presentation.crew

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android4you.space.databinding.FragmentCrewDetailsBinding
import com.android4you.space.domain.model.crew.CrewModel

class CrewDetailsFragment : Fragment() {

    private var _binding: FragmentCrewDetailsBinding? = null

    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCrewDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val crew = arguments?.getParcelable<CrewModel>("crew")
        _binding.apply {
            this?.crews = crew
            this?.crews?.wikipedia?.let {
                setupHyperlink(it)
            }
        }
        _binding?.backButton?.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupHyperlink(link: String) {
        _binding?.wikiValueTextview?.apply {
            this.movementMethod = LinkMovementMethod.getInstance()
            this.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                startActivity(browserIntent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
