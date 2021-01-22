package com.vitor238.covid19brasil.ui.usefullinks

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vitor238.covid19brasil.databinding.FragmentUsefulLinksBinding

class UsefulLinksFragment : Fragment() {

    private var _binding: FragmentUsefulLinksBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterLinks: AdapterLinks

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUsefulLinksBinding.inflate(inflater, container, false)


        adapterLinks = AdapterLinks {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it.link))
            startActivity(browserIntent)
        }
        binding.recyclerLinks.adapter = adapterLinks

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val linksViewModel = ViewModelProvider(this).get(LinksViewModel::class.java)
        linksViewModel.usefulLinks.observe(viewLifecycleOwner) {
            adapterLinks.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}