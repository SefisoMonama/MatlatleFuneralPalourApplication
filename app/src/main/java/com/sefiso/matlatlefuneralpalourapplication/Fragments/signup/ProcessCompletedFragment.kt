package com.sefiso.matlatlefuneralpalourapplication.Fragments.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentProcessCompletedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProcessCompletedFragment : Fragment() {
    private lateinit var binding: FragmentProcessCompletedBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProcessCompletedBinding.inflate(layoutInflater)
        setupUi()
        return binding.root
    }

    private fun setupUi(){

    }
}