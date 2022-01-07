package com.vitor238.covid19brasil.presentation.risks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vitor238.covid19brasil.databinding.FragmentRisksBinding

class RisksFragment : Fragment() {

    private var _binding: FragmentRisksBinding? = null
    private val binding get() = _binding!!
    private val adapter = RiskyActivitiesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRisksBinding.inflate(inflater, container, false)
        binding.riskyActivitiesRecyclerview.adapter = adapter
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val riskyActivitiesViewModel =
            ViewModelProvider(this).get(RiskyActivitiesViewModel::class.java)
        riskyActivitiesViewModel.riskyActivities.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}