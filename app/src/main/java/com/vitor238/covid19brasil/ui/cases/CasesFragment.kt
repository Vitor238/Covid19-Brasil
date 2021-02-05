package com.vitor238.covid19brasil.ui.cases

import android.app.ActivityOptions
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.data.domain.BrazilianState
import com.vitor238.covid19brasil.databinding.FragmentCasesBinding

class CasesFragment : Fragment() {

    private var _binding: FragmentCasesBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var adapterBrazilianStates: AdapterBrazilianStates

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCasesBinding.inflate(inflater, container, false)

        adapterBrazilianStates = AdapterBrazilianStates { view, brazilianState ->
            openDetails(view, brazilianState)
        }

        return binding.root
    }

    private fun openDetails(view: View, brazilianState: BrazilianState) {
        val intent = DetailsActivity.getIntent(requireContext(), brazilianState)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val options = ActivityOptions.makeSceneTransitionAnimation(
                requireActivity(),
                view,
                "transitionActivity"
            )
            requireContext().startActivity(intent, options.toBundle())
        } else {
            startActivity(intent)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val casesViewModel = ViewModelProvider(this).get(CasesViewModel::class.java)

        casesViewModel.casesInBrazil.observe(viewLifecycleOwner) { brazil ->
            brazil?.let {
                binding.textNumberConfirmed.text = brazil.confirmed
                binding.textRecoveredNumber.text = brazil.recovered
                binding.textNumberDeaths.text = brazil.deaths
                binding.textUpdateDate.text = getString(R.string.updatedAtValue,brazil.updatedAt)
            }

        }

        casesViewModel.casesByState.observe(viewLifecycleOwner) {
            binding.recyclerBrazilianStates.adapter = adapterBrazilianStates
            adapterBrazilianStates.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
