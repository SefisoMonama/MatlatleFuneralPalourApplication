package com.sefiso.matlatlefuneralpalourapplication.Fragments.home

import android.app.AlertDialog
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
import com.google.android.material.internal.NavigationMenuView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.sefiso.matlatlefuneralpalourapplication.R
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentHomeScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class  HomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var database: DatabaseReference
    private lateinit var header: NavigationView

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

        //add badge on notification icon
        val badge = binding.bottomNavigationView.getOrCreateBadge(R.id.menuNotification)
        badge.isVisible = true
        badge.number = 2

        //app:headerLayout="@layout/menu_header"-->
        val navigationView = binding.menuItemsNavView
        //Inflate header layout
        val navHeader =  navigationView.inflateHeaderView(R.layout.menu_header)
        //references to views
        val headerImage = navHeader?.findViewById<ImageView>(R.id.imageView)
        headerImage!!.setImageResource(R.drawable.microsoftteams_image__3_)
        val headerFullNames = navHeader.findViewById<TextView>(R.id.title_textView)
        var headerEmail = navHeader.findViewById<TextView>(R.id.email_textView)



        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(FirebaseAuth.getInstance().currentUser!!.uid).get().addOnSuccessListener {
            if(it.exists()){
                val firstName = it.child("name").value
                val surname = it.child("surname").value
                val email = it.child("email").value
                binding.welcomeUserTextView.text = "$firstName"
                headerFullNames?.text = "$firstName $surname"
                headerEmail?.text = "$email"

            }else{
                binding.welcomeUserTextView.visibility = View.GONE
            }
        }

        binding.menuItemsNavView.setNavigationItemSelectedListener {
            if(it.itemId == R.id.helpItem){
                findNavController().navigate(R.id.action_homeScreenFragment_to_claimsFragment)
            }
            true
        }



        //underline text view
        underlineText(binding.covidRulesTextView, string = "Golden rules for covid-19 (UNICEF)")

        /**Handle navigation*/
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


        val callback2 = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                dialog()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback2)

        //Open navigation drawer when menu icon is clicked
        binding.menuImageView.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        //open more fragment (bottomNavigationSheet)
        binding.openLoginSingUpImageView.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreenFragment_to_moreFragment)
        }

        binding.BuyCoverLinearLayout.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreenFragment_to_availablePlansFragment)
        }

        binding.lodgeClaimLayout.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreenFragment_to_claimsFragment)
        }

        binding.coronaLinearLayout.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreenFragment_to_covidFragment)
        }

        binding.contactUsLayout.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreenFragment_to_contactsFragment)
        }

        binding.productsLayout.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreenFragment_to_productsFragment)
        }

        binding.covidRulesTextView.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreenFragment_to_covidGoldenRulesFragment)
        }
    }

    //This function underline specified textView
    private fun underlineText(textView: TextView, string: String) {
        val spannableString = SpannableString(string).apply {
            setSpan(UnderlineSpan(), 0, string.length, 0)
        }
        textView.text = spannableString
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







