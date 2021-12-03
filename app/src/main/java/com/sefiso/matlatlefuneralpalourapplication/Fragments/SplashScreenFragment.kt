package com.sefiso.matlatlefuneralpalourapplication.Fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.sefiso.matlatlefuneralpalourapplication.R
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashScreenBinding.inflate(layoutInflater)
        setupUi()
        return binding.root
    }

    private fun setupUi(){
        val sideAnimation = AnimationUtils.loadAnimation(context, R.anim.side_anim)
        val bottomAnimation = AnimationUtils.loadAnimation(context, R.anim.bottom_anim)

        binding.appNameSplashScreenTextView.animation = sideAnimation
        binding.progressBar.animation = bottomAnimation
        //binding.poweredByTextView.animation = sideAnimation

         Handler().postDelayed(Runnable {
             findNavController().navigate(R.id.action_splashScreenFragment_to_welcomeFragment)
         }, 4000)
    }
}