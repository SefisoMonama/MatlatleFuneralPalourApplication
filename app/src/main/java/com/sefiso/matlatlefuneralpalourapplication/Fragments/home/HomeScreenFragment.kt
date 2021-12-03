package com.sefiso.matlatlefuneralpalourapplication.Fragments.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.sefiso.matlatlefuneralpalourapplication.R
import com.sefiso.matlatlefuneralpalourapplication.data.Initials
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentHomeScreenBinding
import com.sefiso.matlatlefuneralpalourapplication.viewmodels.HomeScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Time
import java.time.Instant

@AndroidEntryPoint
class  HomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var database: DatabaseReference
    private lateinit var viewModel: HomeScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(requireActivity()).get(HomeScreenViewModel::class.java)
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


    @SuppressLint("SetTextI18n")
    private fun setupUi() {

        //animation
        val fromStartAnimation = AnimationUtils.loadAnimation(context, R.anim.side_anim)
        val fromEndAnimation = AnimationUtils.loadAnimation(context, R.anim.bottom_anim)

        //set animation on home screen layout
        binding.BuyCoverLinearLayout.animation = fromStartAnimation
        binding.lodgeClaimLayout.animation = fromStartAnimation
        binding.productsLayout.animation = fromEndAnimation
        binding.contactUsLayout.animation = fromEndAnimation

        //add badge on notification icon
        val badge = binding.bottomNavigationView.getOrCreateBadge(R.id.menuNotification)
        badge.isVisible = true

        val navigationView = binding.menuItemsNavView
        //Inflate header layout
        val navHeader =  navigationView.inflateHeaderView(R.layout.menu_header)
        //references to views
        val headerImage = navHeader.findViewById<ImageView>(R.id.header_imageView)
        val headerFullNames = navHeader.findViewById<TextView>(R.id.title_textView)
        val headerEmail = navHeader.findViewById<TextView>(R.id.email_textView)


        //get user data (name, surname and email) and display them in our home screen
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(FirebaseAuth.getInstance().currentUser!!.uid).get().addOnSuccessListener {
            if(it.exists()){
                val firstName = it.child("name").value
                val surname = it.child("surname").value
                val email = it.child("email").value
                binding.welcomeUserTextView.text = "$firstName"
                headerFullNames?.text = "$firstName $surname"
                headerEmail?.text = "$email"

                //set default initial drawable in the home screen
                val initial = firstName.toString()[0]
                val image = Initials(initial.toString())
                binding.menuImageView.setImageResource(image.getInitialImageResource())

                //also add default drawable in menu side drawer
                headerImage!!.setImageResource(image.getInitialImageResource())
            }else{
                binding.welcomeUserTextView.visibility = View.GONE
            }
        }

        //greet user
        binding.appNameTextView.text = "Hello,"


        //underline text view
        underlineText(binding.covidRulesTextView, text = "Golden rules for covid-19 (UNICEF)")

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
    private fun underlineText(textView: TextView, text: String) {
        val spannableString = SpannableString(text).apply {
            setSpan(UnderlineSpan(), 0, text.length, 0)
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







