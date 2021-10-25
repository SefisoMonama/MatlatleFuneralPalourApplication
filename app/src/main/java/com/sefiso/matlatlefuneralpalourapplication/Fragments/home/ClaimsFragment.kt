package com.sefiso.matlatlefuneralpalourapplication.Fragments.home

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.sefiso.matlatlefuneralpalourapplication.R
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentClaimsBinding

class ClaimsFragment : Fragment() {
   private lateinit var binding: FragmentClaimsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentClaimsBinding.inflate(layoutInflater)
        setupUi()
        return binding.root
    }

    private fun setupUi(){
        binding.claimToolbar.setNavigationIcon(R.drawable.ic_back)
        binding.claimToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_claimsFragment_to_homeScreenFragment)
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.homeScreenFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        binding.useSaIdSwitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                binding.policyHolderTextInputLayout.hint = "Policyholder ID Number"
            }else{
                binding.policyHolderTextInputLayout.hint = "Policyholder passport Number"
            }
        }

        binding.submitClaimButton.setOnClickListener {
            dialog(layoutInflater.inflate(R.layout.dialog_layout, null))
        }
    }

    private fun dialog(view: View){
        val dialog = AlertDialog.Builder(context)
        dialog.setPositiveButton("OK"){dialog, _ -> dialog.dismiss() }
        dialog.setMessage("We acknowledge your request to claim. A service agent will contact you shortly.")
        dialog.setView(view)
        dialog.show()
    }

}