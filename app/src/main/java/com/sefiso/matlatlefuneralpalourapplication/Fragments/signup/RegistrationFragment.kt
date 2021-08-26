package com.sefiso.matlatlefuneralpalourapplication.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sefiso.matlatlefuneralpalourapplication.R
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment() {
   private lateinit var binding: FragmentRegistrationBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(layoutInflater)

        //add a back android icon to navigate back to login fragment
        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_requestQuoteFragment)
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_createUsernamePasswordFragment)
        }
        return binding.root
    }
}