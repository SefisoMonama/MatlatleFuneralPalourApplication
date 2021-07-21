package com.sefiso.matlatlefuneralpalourapplication.Fragments

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import com.sefiso.matlatlefuneralpalourapplication.R
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentHomeScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        //settingsViewModel = ViewModelProvider(requireActivity()).get(SettingsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeScreenBinding.inflate(layoutInflater)
        setupUi()
        return binding.root
    }



    private fun setupUi() {

        val drawer = binding.drawerLayout

        //Handle onBackPressed
        //Close the drawer first when you press traditional android back button, if the drawer was open
        //This will avoid closing the entire activity when you had your drawer open
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START)
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        //Animate the view specified
        animateImageView(binding.loginCardView)
        animateImageView(binding.buyNowCardView)
        animateImageView(binding.claimCardView)
        animateImageView(binding.matlatleMobileCardView)

        //navigate to login fragment when loginCardView is clicked
        binding.loginCardView.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreenFragment_to_loginFragment)
        }

        //add menu icon on toolBar
        //add onClickListener on menuIcon to open drawerLayout
        val menuIcon = binding.toolbar3
        menuIcon.setNavigationIcon(R.drawable.ic_menu)
        menuIcon.setNavigationOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }

    }

    //set flip animation on every cardView specified
    private fun animateImageView(cardView: MaterialCardView) {
        val alpha = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0F, 1F)
        ObjectAnimator.ofPropertyValuesHolder(cardView, alpha)
            .apply {
                duration = 600
            }.start()
    }
}






