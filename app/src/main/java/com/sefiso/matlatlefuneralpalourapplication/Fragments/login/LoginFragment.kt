package com.sefiso.matlatlefuneralpalourapplication.Fragments.login

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Patterns
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sefiso.matlatlefuneralpalourapplication.R
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentLoginBinding
import com.sefiso.matlatlefuneralpalourapplication.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.sql.Time
import java.time.Instant
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@AndroidEntryPoint
class LoginFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
    }

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
            showMessage("Logging In....")
            loginUser()
        }

        binding.signUpNowLinearLayout.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        underlineText(binding.forgotPasswordTextView, "Forgot Password?")
    }

   fun loginUser() {
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

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.editTextTextEmailAddress.error =
                "Please provide valid email address 'E.G Matlatle@gmail.com'"
            binding.editTextTextEmailAddress.requestFocus()
            return
        }

        lifecycleScope.launch {
            auth = Firebase.auth
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            launch {
                                //showProgressBar(true)
                                binding.progressBar.visibility = View.VISIBLE
                                delay(3000)
                                if (task.isSuccessful) {
                                    // Sign in success, update UI with the signed-in user's information
                                    val user = auth.currentUser
                                    //showProgressBar(false)
                                    binding.progressBar.visibility = View.GONE
                                    findNavController().navigate(R.id.action_loginFragment_to_homeScreenFragment)
                                } else {  
                                    binding.progressBar.visibility = View.GONE
                                    binding.errorTextView.visibility = View.VISIBLE
                                    binding.editTextTextPassword.text.clear()
                                    showMessage("Couldn't authenticate")
                                }
                            } 
                        }
                }
            }


    //This function underline specified textView
    private fun underlineText(textView: TextView, text: String) {
        val spannableString = SpannableString(text).apply {
            setSpan(UnderlineSpan(), 0, text.length, 0)
        }
        textView.text = spannableString
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}