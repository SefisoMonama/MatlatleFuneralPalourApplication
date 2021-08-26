package com.sefiso.matlatlefuneralpalourapplication.Fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
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
        //Handle onBackPressed
        //Close the drawer first when you press traditional android back button, if the drawer was open
        //This will avoid closing the entire activity when you had your drawer open
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    activity!!.supportFragmentManager.popBackStack()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        //Open navigation drawer when menu icon is clicked
        binding.menuImageView.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        //open more fragment (bottomNavigationSheet)
        binding.openLoginSingUpImageView.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreenFragment_to_moreFragment)
        }

        //add onClickListener on menuIcon to open/move to proper destination
        /**binding.bottomNavigationView.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.moreItemsMenu -> binding.drawerLayout.openDrawer(GravityCompat.END)
                //R.id.menuProfile -> findNavController().navigate(R.id.action_homeScreenFragment_to_profileFragment)
                R.id.menuClaim -> findNavController().navigate(R.id.action_homeScreenFragment_to_claimsFragment)
                R.id.menuNotification -> findNavController().navigate(R.id.action_homeScreenFragment_to_messagesFragment)
            }
        }

        //open more fragment (bottomNavigationSheet)
        binding.moreImageView.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreenFragment_to_moreFragment2)
        }

        //add badge on message icon
        var badge = binding.bottomNavigationView.getOrCreateBadge(R.id.menuNotification)
        badge.isVisible = true
        badge.number = 2*/
    }
}






