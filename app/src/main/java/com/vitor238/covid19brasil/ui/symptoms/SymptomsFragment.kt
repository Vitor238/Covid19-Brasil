package com.vitor238.covid19brasil.ui.symptoms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.databinding.FragmentCareBinding
import com.vitor238.covid19brasil.databinding.FragmentSymptomsBinding

class SymptomsFragment : Fragment() {

    private var _binding: FragmentSymptomsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSymptomsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
