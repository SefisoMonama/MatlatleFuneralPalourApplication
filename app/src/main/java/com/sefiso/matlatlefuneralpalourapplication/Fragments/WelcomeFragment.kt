package com.sefiso.matlatlefuneralpalourapplication.Fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sefiso.matlatlefuneralpalourapplication.NetworkListener
import com.sefiso.matlatlefuneralpalourapplication.R
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentWelcomeBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WelcomeFragment : Fragment() {

    private lateinit var binding : FragmentWelcomeBinding
    private lateinit var networkListener: NetworkListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWelcomeBinding.inflate(layoutInflater)
        setupUi()

        return binding.root
    }

    private  fun setupUi(){

        val fromTopAnim = AnimationUtils.loadAnimation(context, R.anim.from_top_anim)
        binding.crossImageView.animation = fromTopAnim

        val callback2 = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                dialog()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback2)

        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
        }

        binding.signupTextView.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_signupFragment)
        }
    }

    private fun dialog() {
        val dialog = AlertDialog.Builder(context)
        dialog.setMessage("You are about to exit the application.")
            .setCancelable(false)
            .setPositiveButton("EXIT"){dialog, _ -> requireActivity().finish()}
            .setNegativeButton("CANCEL") { dialog, _ -> dialog.dismiss() }
            .setTitle("Exiting App")
        dialog.create()
        dialog.show()
    }
}