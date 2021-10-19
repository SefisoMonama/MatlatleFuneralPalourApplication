package com.sefiso.matlatlefuneralpalourapplication.Fragments.login

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.util.Patterns
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sefiso.matlatlefuneralpalourapplication.R
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        setupUi()
        container?.clearDisappearingChildren()
        return binding.root
    }

    private fun setupUi() {
        //move to forgot password fragment
        binding.forgotPasswordTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        binding.loginButton.setOnClickListener {
            Toast.makeText(context, "Logging In....", Toast.LENGTH_SHORT).show()
            loginUser()
        }

        binding.signUpNowLinearLayout.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_createUsernamePasswordFragment)
        }

        underlineText(binding.forgotPasswordTextView, "Forgot Password?")
    }

    private fun loginUser() {
        auth = Firebase.auth
        val email = binding.editTextTextEmailAddress.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()

        if (email.isEmpty()) {
            binding.editTextTextEmailAddress.error = "Please enter email address"
            binding.editTextTextEmailAddress.requestFocus()
            return
        }

        if (password.isEmpty()) {
            binding.editTextTextPassword.error = "Please enter password"
            binding.editTextTextPassword.requestFocus()
            binding.editTextTextPassword.text.clear()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.editTextTextEmailAddress.error = "Please provide valid email address 'E.G Matlatle@gmail.com'"
            binding.editTextTextEmailAddress.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    lifecycleScope.launch {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.progressBar.isIndeterminate = true
                        delay(1000)

                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser
                            binding.progressBar.visibility = View.GONE
                            binding.progressBar.isIndeterminate = false
                            findNavController().navigate(R.id.action_loginFragment_to_homeScreenFragment)
                        } else {
                            // If sign in fails, display a message to the user.
                            lifecycleScope.launch {
                                delay(1000)
                                Toast.makeText(context, "Authentication failed. Try again!",
                                        Toast.LENGTH_SHORT).show()
                                binding.editTextTextPassword.text.clear()
                                binding.progressBar.visibility = View.GONE
                                binding.progressBar.isIndeterminate = false
                            }
                        }
                    }
                }
    }

    //This function underline specified textView
    private fun underlineText(textView: TextView, string: String) {
        val spannableString = SpannableString(string).apply {
            setSpan(UnderlineSpan(), 0, string.length, 0)
        }
        textView.text = spannableString
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }
}