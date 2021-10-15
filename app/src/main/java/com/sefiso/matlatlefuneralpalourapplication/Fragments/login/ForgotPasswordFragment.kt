package com.sefiso.matlatlefuneralpalourapplication.Fragments.login

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sefiso.matlatlefuneralpalourapplication.R
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentForgotPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgotPasswordBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForgotPasswordBinding.inflate(layoutInflater)
        container?.clearDisappearingChildren()
        setupUi()
        return binding.root
    }

    private fun setupUi(){

        // add a back android icon to navigate back to login fragment
        binding.forgotPasswordToolbar.setNavigationIcon(R.drawable.ic_back)
        binding.forgotPasswordToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_welcomeFragment)
        }

        binding.updateButton.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            passwordReset()
        }
    }

    private fun passwordReset(){

        val email = binding.emailEditTextTextEmailAddress.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailEditTextTextEmailAddress.error = "Please provide valid email"
            binding.emailEditTextTextEmailAddress.requestFocus()
            return
        }

        if (email.isEmpty()){
            binding.emailEditTextTextEmailAddress.error = "Email is required"
            binding.emailEditTextTextEmailAddress.requestFocus()
            return
        }

        Firebase.auth.sendPasswordResetEmail(email)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        binding.progressBar.visibility = View.GONE
                        var dialog = AlertDialog.Builder(context)
                        dialog.setMessage("Password reset link was send to the mail address provided.")
                        dialog.setPositiveButton("OK"){dialog, _ -> findNavController().navigate(R.id.action_forgotPasswordFragment_to_welcomeFragment)}
                        dialog.setTitle("Reset Password")
                        dialog.show()
                    }else{
                        binding.progressBar.visibility = View.GONE
                        var dialog = AlertDialog.Builder(context)
                        dialog.setMessage("Please ensure the mail provided matches the one you previously registered with, and try again.")
                        dialog.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                        dialog.setTitle("Couldn't send link")
                        dialog.show()
                    }
                }
    }
}