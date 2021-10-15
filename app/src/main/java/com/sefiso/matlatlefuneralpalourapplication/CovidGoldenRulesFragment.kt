package com.sefiso.matlatlefuneralpalourapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentCovidGoldenRulesBinding

class CovidGoldenRulesFragment : Fragment() {

    private lateinit var binding: FragmentCovidGoldenRulesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCovidGoldenRulesBinding.inflate(layoutInflater)
        setupUi()
        return binding.root
    }

    private fun setupUi(){

        //load the fragment with url provide when navigate to this fragment
        binding.covidGoldenRulesWebView.webViewClient
        binding.covidGoldenRulesWebView.loadUrl("https://www.unicef.org/southafrica/documents/golden-rules-preventing-spread-covid-19")

        //when traditional back button pressed navigate to home fragment
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.homeScreenFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        //When toolbar back icon pressed navigate to home screen
        binding.covidGoldenRuleToolbar.setNavigationIcon(R.drawable.ic_back)
        binding.covidGoldenRuleToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_covidGoldenRulesFragment_to_homeScreenFragment)
        }

        //When floating home action bar icon pressed navigate to home screen
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_covidGoldenRulesFragment_to_homeScreenFragment)
        }
    }
}