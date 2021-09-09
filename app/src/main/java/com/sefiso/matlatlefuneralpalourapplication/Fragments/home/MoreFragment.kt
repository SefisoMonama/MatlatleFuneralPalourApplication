package com.sefiso.matlatlefuneralpalourapplication.Fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sefiso.matlatlefuneralpalourapplication.R
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentMoreBinding


class MoreFragment : BottomSheetDialogFragment() {

    private  lateinit var binding: FragmentMoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMoreBinding.inflate(layoutInflater)
        setupUi()
        return binding.root
    }

    private fun setupUi(){
        binding.loginSignUpImageView.setOnClickListener {
            findNavController().navigate(R.id.action_moreFragment_to_requestQuoteFragment)
        }
        binding.loginSignUpTextView.setOnClickListener {
            findNavController().navigate(R.id.action_moreFragment_to_requestQuoteFragment)
        }
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }
}