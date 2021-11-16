package com.sefiso.matlatlefuneralpalourapplication.Fragments.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.sefiso.matlatlefuneralpalourapplication.*
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(layoutInflater)
        setupUi()
        return binding.root
    }

    private fun setupUi() {

        //navigate to Create Username fragment when selected to sign in with Email and password.
        binding.signInEmailButton.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_createUsernamePasswordFragment)
        }

        //when traditional back button pressed move to welcome fragment
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.welcomeFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    
}