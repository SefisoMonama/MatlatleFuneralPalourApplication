package com.sefiso.matlatlefuneralpalourapplication.Fragments.signup

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout
import com.sefiso.matlatlefuneralpalourapplication.*
import com.sefiso.matlatlefuneralpalourapplication.Fragments.login.LoginFragment
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentRequestQuoteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RequestQuoteFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentRequestQuoteBinding
    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRequestQuoteBinding.inflate(layoutInflater)
        setupUi()
        return binding.root
    }

    private fun setupUi(){
        tabLayout = binding.tabLayout
        viewPager = binding.loginViewPager
        val fb = binding.facebookFab
        val google = binding.googleFab
        val home = binding.callFab

        //add content and title on our viewPager
        val adapter = PagerAdapter(requireFragmentManager())
        adapter.addFragment(LoginFragment(), "Login")
        adapter.addFragment(SignupFragment(), "SignUp")
        viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(viewPager)

        //open dialer when call floating action bar is clicked
        binding.callFab.setOnClickListener {
            lifecycleScope.launch {
                Toast.makeText(context, "Opening Dialer...", Toast.LENGTH_SHORT).show()
                delay(3000)
                startCall()
            }
        }

        //Underlining specified textView
        underlineText(binding.backToHomeTextView, getString(R.string.back_to_home_screen))
    }

    //This function underline specified textView
    private fun underlineText(textView: TextView, string: String) {
        val spannableString = SpannableString(string).apply {
            setSpan(UnderlineSpan(), 0, string.length, 0)
        }
        textView.text = spannableString
    }

    //This function will open dialer with the tel number specified already dialed
    private fun startCall() {
        val intent = Intent(Intent.ACTION_DIAL);
        intent.data = Uri.parse("tel:0723377446")
        startActivity(intent)
    }

}


