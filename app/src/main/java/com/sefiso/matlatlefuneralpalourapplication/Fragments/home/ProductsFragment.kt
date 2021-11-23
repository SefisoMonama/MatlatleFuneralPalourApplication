package com.sefiso.matlatlefuneralpalourapplication.Fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.sefiso.matlatlefuneralpalourapplication.R
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductsBinding.inflate(layoutInflater)
        setupUi()
        return binding.root
    }

    private fun setupUi(){

        binding.productsToolbar.setNavigationIcon(R.drawable.ic_back)
        binding.productsToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_productsFragment_to_homeScreenFragment)
        }

        //for Funeral dignity plan text view
        binding.funeralDignityPlanTextView.setOnClickListener {
            binding.UntimateDignityPlanTextView.visibility = View.GONE
            binding.matlatlePlanDescTextView.visibility = View.GONE
            binding.funeralDignityPlanTextView.visibility = View.GONE
            binding.funeralDignityPlanInfoLinearLayout.visibility = View.VISIBLE
        }

        //for Ultimate dignity plan text view
        binding.UntimateDignityPlanTextView.setOnClickListener {
            binding.funeralDignityPlanTextView.visibility = View.GONE
            binding.matlatlePlanDescTextView.visibility = View.GONE
            binding.UntimateDignityPlanTextView.visibility = View.GONE
            binding.ultimateDignityPlanInfoLinearLayout.visibility = View.VISIBLE

        }

        binding.funeralDignityPlanInfoLinearLayout.setOnClickListener {
            binding.matlatlePlanDescTextView.visibility = View.VISIBLE
            binding.funeralDignityPlanInfoLinearLayout.visibility= View.GONE
            binding.funeralDignityPlanTextView.visibility = View.VISIBLE
            binding.UntimateDignityPlanTextView.visibility = View.VISIBLE
            binding.ultimateDignityPlanInfoLinearLayout.visibility = View.GONE
        }

        binding.ultimateDignityPlanInfoLinearLayout.setOnClickListener {
            binding.matlatlePlanDescTextView.visibility = View.VISIBLE
            binding.ultimateDignityPlanInfoLinearLayout.visibility= View.GONE
            binding.funeralDignityPlanTextView.visibility = View.VISIBLE
            binding.UntimateDignityPlanTextView.visibility = View.VISIBLE
            binding.ultimateDignityPlanInfoLinearLayout.visibility = View.GONE
        }

        //when traditional back button pressed move to home fragment
        val callback2 = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.homeScreenFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback2)
    }
}