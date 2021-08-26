package com.sefiso.matlatlefuneralpalourapplication.Fragments.login

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.sefiso.matlatlefuneralpalourapplication.R
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentForgotPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgotPasswordBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForgotPasswordBinding.inflate(layoutInflater)
        container?.clearDisappearingChildren()

        //navigate back to login fragment
        binding.backToLoginTextView.setOnClickListener {
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
        }

        // add a back android icon to navigate back to login fragment
        binding.forgotPasswordToolbar.setNavigationIcon(R.drawable.ic_back)
        binding.forgotPasswordToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
        }

        underlineText(binding.backToLoginTextView, getString(R.string.back_to_login))

        return binding.root
    }


    //This function underline specified textView
    private fun underlineText(textView: TextView, string: String) {
        val spannableString = SpannableString(string).apply {
            setSpan(UnderlineSpan(), 0, string.length, 0)
        }
        textView.text = spannableString
    }
}