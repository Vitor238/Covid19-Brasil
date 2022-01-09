package com.vitor238.covid19brasil.presentation.cases

import android.app.ActivityOptions
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.databinding.FragmentCasesBinding
import com.vitor238.covid19brasil.domain.model.BrazilianState
import org.koin.androidx.viewmodel.ext.android.viewModel

class CasesFragment : Fragment() {

    private var _binding: FragmentCasesBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var adapterBrazilianStates: AdapterBrazilianStates
    private val casesViewModel by viewModel<CasesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCasesBinding.inflate(inflater, container, false)
        setupAdapter()
        observeViewModels()
        return binding.root
    }

    private fun observeViewModels() {
        casesViewModel.getCasesInBrazil()
        casesViewModel.getCasesByState()

        casesViewModel.casesInBrazil.observe(viewLifecycleOwner) { brazil ->
            brazil?.let {
                binding.materialCardConfirmed.isVisible = brazil.confirmed != "0"
                binding.materialCardDeaths.isVisible = brazil.deaths != "0"
                binding.materialCardRecovered.isVisible = brazil.recovered != "0"
                binding.textNumberConfirmed.text = brazil.confirmed
                binding.textRecoveredNumber.text = brazil.recovered
                binding.textNumberDeaths.text = brazil.deaths
                binding.textUpdateDate.text = getString(R.string.updatedAtValue, brazil.updatedAt)
            }

        }

        casesViewModel.casesByState.observe(viewLifecycleOwner) {
            binding.recyclerBrazilianStates.adapter = adapterBrazilianStates
            adapterBrazilianStates.submitList(it)
        }

    }

    private fun setupAdapter() {
        adapterBrazilianStates = AdapterBrazilianStates { view, brazilianState ->
            openDetails(view, brazilianState)
        }
    }

    private fun openDetails(view: View, brazilianState: BrazilianState) {
        val intent = DetailsActivity.getIntent(requireContext(), brazilianState)
        val options = ActivityOptions.makeSceneTransitionAnimation(
            requireActivity(),
            view,
            "transitionActivity"
        )
        requireContext().startActivity(intent, options.toBundle())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
