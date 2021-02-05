package com.vitor238.covid19brasil.ui.symptoms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.databinding.FragmentCareBinding
import com.vitor238.covid19brasil.databinding.FragmentSymptomsBinding
import com.vitor238.covid19brasil.utils.extension.fromHTML

class SymptomsFragment : Fragment() {

    private var _binding: FragmentSymptomsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSymptomsBinding.inflate(inflater, container, false)

        binding.textCommonSymptomsList.text = getString(R.string.common_symptoms_list).fromHTML()
        binding.textLessCommonSymptomsList.text = getString(R.string.less_common_symptoms_list).fromHTML()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
