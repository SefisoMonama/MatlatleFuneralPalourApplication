package com.sefiso.matlatlefuneralpalourapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentSuccessfullyRegisteredAlertDialogBinding

class SuccessfullyRegisteredAlertDialogFragment : Fragment() {

    private lateinit var binding: FragmentSuccessfullyRegisteredAlertDialogBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentSuccessfullyRegisteredAlertDialogBinding.inflate(layoutInflater)
        setupUi()
        return binding.root
    }

    private fun setupUi(){

    }
}