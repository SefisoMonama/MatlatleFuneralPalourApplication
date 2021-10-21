package com.sefiso.matlatlefuneralpalourapplication.Fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sefiso.matlatlefuneralpalourapplication.R
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentCovidBinding

class CovidFragment : Fragment() {

    private lateinit var binding: FragmentCovidBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentCovidBinding.inflate(layoutInflater)
        setupUi()
        return binding.root
    }

    private fun setupUi(){

        binding.covidWebview.webViewClient
        binding.covidWebview.loadUrl("https://sacoronavirus.co.za/")

        binding.covidWebViewToolbar.setNavigationIcon(R.drawable.ic_back)
        binding.covidWebViewToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_covidFragment_to_homeScreenFragment)
        }

        binding.covidWebViewFloatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_covidFragment_to_homeScreenFragment)
        }
    }
}