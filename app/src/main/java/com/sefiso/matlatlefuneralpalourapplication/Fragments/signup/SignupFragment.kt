package com.sefiso.matlatlefuneralpalourapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(layoutInflater)
        binding.startSignUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_registrationFragment)
        }
        return binding.root
    }
}