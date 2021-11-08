package com.sefiso.matlatlefuneralpalourapplication.Fragments.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Patterns
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sefiso.matlatlefuneralpalourapplication.R
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentLoginBinding
import com.sefiso.matlatlefuneralpalourapplication.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

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
            findNavController().navigate(R.id.action_loginFragment_to_createUsernamePasswordFragment)
        }

        underlineText(binding.forgotPasswordTextView, "Forgot Password?")
    }

    private fun loginUser() {
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
            binding.editTextTextEmailAddress.error = "Please provide valid email address 'E.G Matlatle@gmail.com'"
            binding.editTextTextEmailAddress.requestFocus()
            return
        }

        lifecycleScope.launch {
            viewModel.signInWithEmailAndPassword(email, password)
            viewModel.loginSuccessful.observe(viewLifecycleOwner) {
                it?.let {
                    if (it) {
                        findNavController().navigate(R.id.action_loginFragment_to_homeScreenFragment)
                    }else{
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

    private fun showMessage(message:String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}