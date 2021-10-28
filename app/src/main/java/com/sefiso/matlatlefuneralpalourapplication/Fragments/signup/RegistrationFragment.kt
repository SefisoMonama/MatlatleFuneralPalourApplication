package com.sefiso.matlatlefuneralpalourapplication.Fragments.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sefiso.matlatlefuneralpalourapplication.R
import com.sefiso.matlatlefuneralpalourapplication.User
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var user: User

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(layoutInflater)
        setupUi()
        subscribeUi()
        return binding.root
    }

    private fun setupUi() {

        //when traditional back button pressed move to signUp fragment
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.signupFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        //add a back android icon to navigate back to login fragment
        auth = Firebase.auth
        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_signupFragment)
        }

        binding.continueButton.setOnClickListener {
            registerUser().runCatching {
            }

            //findNavController().navigate(R.id.action_registrationFragment_to_createUsernamePasswordFragment)
        }
    }

    private fun subscribeUi() {

    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            //reload();
        }
    }

    private fun registerUser() {
        val name = binding.firstNameEditText.text.toString().trim()
        val surname = binding.lastNameEditText.text.toString().trim()
        val idNumber = binding.idNumberEditText.text.toString().trim()
        val contactDetails = binding.contactDetailsEditText.text.toString().trim()

        if (name.isEmpty()) {
            binding.firstNameEditText.error = "First name is required"
            binding.firstNameEditText.requestFocus()
            return
        }
        if (surname.isEmpty()) {
            binding.lastNameEditText.error = "Last name is required"
            binding.lastNameEditText.requestFocus()
            return
        }
        if (idNumber.isEmpty() || idNumber.length < 13) {
            binding.idNumberEditText.error = "ID Number is required and must be 13 digit"
            binding.idNumberEditText.requestFocus()
            return
        }
        if (contactDetails.isEmpty()) {
            binding.contactDetailsEditText.error = "Contact details are required"
            binding.contactDetailsEditText.requestFocus()
            return
        }
        if (!contactDetails.startsWith("0")) {
            binding.contactDetailsEditText.error = "Contact details must start with 0"
            binding.contactDetailsEditText.requestFocus()
            return
        }
        if (contactDetails.length > 10 || contactDetails.length < 10) {
            binding.contactDetailsEditText.error = "Contact details must be 10 digits"
            binding.contactDetailsEditText.requestFocus()
            return
        }

        lifecycleScope.launch {
            binding.progressBar.visibility = View.VISIBLE
            binding.progressBar.isIndeterminate = true
            delay(500)
            findNavController().navigate(R.id.action_registrationFragment_to_createUsernamePasswordFragment)
        }
    }
}