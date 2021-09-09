package com.sefiso.matlatlefuneralpalourapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentAvailablePlansBinding

class AvailablePlansFragment : Fragment() {

    private lateinit var binding: FragmentAvailablePlansBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAvailablePlansBinding.inflate(layoutInflater)
        setupUi()
        return binding.root
    }

    private fun setupUi(){
        binding.availablePlansToolbar.setNavigationIcon(R.drawable.ic_back)
        binding.availablePlansToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_availablePlansFragment_to_homeScreenFragment)
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.homeScreenFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)


    }

}