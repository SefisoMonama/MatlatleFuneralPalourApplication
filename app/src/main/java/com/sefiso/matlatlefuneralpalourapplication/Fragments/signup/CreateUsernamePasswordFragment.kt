package com.sefiso.matlatlefuneralpalourapplication.Fragments.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.sefiso.matlatlefuneralpalourapplication.R
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentCreateUsernamePasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateUsernamePasswordFragment : Fragment() {
    private lateinit var binding: FragmentCreateUsernamePasswordBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateUsernamePasswordBinding.inflate(layoutInflater)
        setupUi()
        return binding.root
    }

    private fun setupUi() {

        val toolbar = binding.toolbar
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_createUsernamePasswordFragment_to_registrationFragment)
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_createUsernamePasswordFragment_to_oneTimePinFragment)
        }

        var password = binding.passwordTextInputEditText.text.toString()
        var confirmPassword = binding.confirmPasswordTextInputEditText.text.toString()

        //check username length is greater than 8 characters
        binding.usernameTextInputEditText.doOnTextChanged { text, start, before, count ->

            //Check if the user name contains more than 7 characters
            if (text!!.length >= 8) {
                binding.charTooLongImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
                binding.charTooLongTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
                binding.usernameTextInputLayout.error = null
            } else {
                binding.charTooLongImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.grey))
                binding.charTooLongTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                binding.usernameTextInputLayout.error = null
            }

            //check if the edit text is empty
            if (text!!.isEmpty()) {
                binding.usernameTextInputLayout.error = "this field is required"
            } else {
                binding.usernameTextInputLayout.error = null
            }

            //iterate through every single character of the text
            for (i in text) {
                //check for atleast 1 uppercase letter
                if (i.isUpperCase()) {
                    binding.capitalLetterImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
                    binding.capitalLetterTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
                } else {
                    binding.capitalLetterImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.grey))
                    binding.capitalLetterTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                }

                //check for at-least 1 Digit
                if (i.isDigit()) {
                    binding.charAndNumberImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
                    binding.charAndNumberTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
                } else {
                    binding.charAndNumberImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.grey))
                    binding.charAndNumberTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                }


                if (text.length >= 8) {
                    if(confirmPassword == password) {
                        binding.floatingActionButton.isEnabled = true
                    }
                }

            }
        }
    }
}
