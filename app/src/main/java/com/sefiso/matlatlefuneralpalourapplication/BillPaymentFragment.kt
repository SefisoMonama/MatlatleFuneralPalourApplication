package com.sefiso.matlatlefuneralpalourapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentBillPaymentBinding


class BillPaymentFragment : Fragment() {

    private lateinit var binding: FragmentBillPaymentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBillPaymentBinding.inflate(layoutInflater)
        setupUi()
        return binding.root
    }

    private fun setupUi(){

        /**navigate back to home fragment*/
        //when traditional back button pressed move to home fragment
        val callback2 = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.homeScreenFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback2)

        //
        binding.billPaymentToolbar.setNavigationIcon(R.drawable.ic_back)
        binding.billPaymentToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_billPaymentFragment_to_homeScreenFragment)
        }

        //
        binding.homeFloatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_billPaymentFragment_to_homeScreenFragment)
        }
    }
}