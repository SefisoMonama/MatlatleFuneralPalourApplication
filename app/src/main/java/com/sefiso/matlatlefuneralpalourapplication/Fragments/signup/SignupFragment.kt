package com.sefiso.matlatlefuneralpalourapplication.Fragments.signup

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
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
        binding.signupWithEmailTextView.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_createUsernamePasswordFragment)
        }

        //when traditional back button pressed move to welcome fragment
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.welcomeFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        //navigate to login fragment
        binding.loginButton.setOnClickListener{
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }


        //open "create gmail account" using intent, for user to create a new gmail account
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://accounts.google.com/signup/v2/webcreateaccount?service=accountsettings&continue=https%3A%2F%2Fmyaccount.google.com%2F%3Fhl%3Den%26utm_source%3DOGB%26utm_medium%3Dact%26pli%3D1&hl=en&dsh=S1761868391%3A1647352474328357&biz=false&flowName=GlifWebSignIn&flowEntry=SignUp"))
        startActivity(browserIntent)
    }
}