package com.sefiso.matlatlefuneralpalourapplication.Fragments.signup

import android.app.AlertDialog
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sefiso.matlatlefuneralpalourapplication.R
import com.sefiso.matlatlefuneralpalourapplication.User
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentCreateUsernamePasswordBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CreateUsernamePasswordFragment : Fragment() {

    private lateinit var binding: FragmentCreateUsernamePasswordBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateUsernamePasswordBinding.inflate(layoutInflater)
        setupUi()
        return binding.root
    }

    private fun setupUi() {

        auth = Firebase.auth
        database = Firebase.database.reference

        //when traditional back button pressed move to signUp fragment
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.signupFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        //add android back button and navigate back to reg fragment when selected
        val toolbar = binding.toolbar
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_createUsernamePasswordFragment_to_signupFragment)
        }

        //move to one time pin fragment when continue button is clicked
        binding.registerButton.setOnClickListener {
            Toast.makeText(context, "Authenticating...", Toast.LENGTH_LONG).show()
            registerUser()
        }
    }

    private fun registerUser() {
        val email = binding.emailEditText.text.toString().trim()
        val password = binding.passwordEditText.text.toString().trim()
        val confirmPassword = binding.confirmPasswordEditText.text.toString().trim()
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


        if (email.isEmpty()) {
            binding.emailEditText.error = "Email is required"
            binding.emailEditText.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailEditText.error = "Please provide valid email"
            binding.emailEditText.requestFocus()
            return
        }
        if (password.isEmpty()) {
            binding.passwordEditText.error = "Password is required"
            binding.passwordEditText.requestFocus()
            return
        }

        if (confirmPassword != password) {
            binding.confirmPasswordEditText.error = "Password doesn't match, please retry."
            binding.confirmPasswordEditText.clearFocus()
            binding.confirmPasswordEditText.requestFocus()
            return
        }

        if(!binding.checkBox.isChecked){
            binding.checkBox.error = "Accept terms and condition to continue"
            binding.checkBox.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val newUser = User()
                        newUser.User(name, surname, idNumber, contactDetails, email)
                        val user = auth.currentUser
                        binding.progressBar.visibility = View.GONE
                        binding.progressBar.isIndeterminate = true
                        user!!.sendEmailVerification()
                        dialog()
                        //write data to realtime database
                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().currentUser!!.uid)
                                .setValue(newUser)
                    } else {
                        // If sign in fails, display a message to the user.
                        binding.progressBar.visibility = View.GONE
                        binding.progressBar.isIndeterminate = false
                        Toast.makeText(context, "Authentication failed, Email is already used.", Toast.LENGTH_LONG).show()
                    }
                }
    }

    //when this function is called it open information dialog - alertDialog
    private fun dialog() {
        val dialog = AlertDialog.Builder(context)
        dialog.setMessage("You've successfully registered as a user to Matlatle application, You'll receive an Email/SMS depending on your registration method. Click 'OK' to proceed to our home page.")
                .setCancelable(false)
                .setPositiveButton("OK") { dialog, _ -> findNavController().navigate(R.id.action_createUsernamePasswordFragment_to_homeScreenFragment) }
        dialog.create()
        dialog.setTitle("Registered Successfully")
        dialog.show()
    }
}

