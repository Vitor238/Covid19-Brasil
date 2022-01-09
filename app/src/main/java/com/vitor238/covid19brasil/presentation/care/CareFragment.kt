package com.vitor238.covid19brasil.presentation.care

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.databinding.FragmentCareBinding
import com.vitor238.covid19brasil.utils.extension.fromHTML

class CareFragment : Fragment() {

    private var _binding: FragmentCareBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCareBinding.inflate(inflater, container, false)

        binding.textPreventionList.text = getString(R.string.prevention_list).fromHTML()
        binding.textTransmissionList.text = getString(R.string.transmission_list).fromHTML()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
