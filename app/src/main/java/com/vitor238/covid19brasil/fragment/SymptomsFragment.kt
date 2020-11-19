package com.vitor238.covid19brasil.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vitor238.covid19brasil.R

class SymptomsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_symptoms, container, false)
    }

    companion object {
        fun newInstance() = SymptomsFragment()
    }
}
