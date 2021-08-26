package com.sefiso.matlatlefuneralpalourapplication.Fragments


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sefiso.matlatlefuneralpalourapplication.R
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentOneTimePinBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.w3c.dom.Text

@AndroidEntryPoint
class OneTimePinFragment : androidx.fragment.app.Fragment() {
    private lateinit var binding: FragmentOneTimePinBinding
    private  lateinit var currTextView: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOneTimePinBinding.inflate(layoutInflater)
        setupUi()
        return binding.root
    }

    private fun setupUi(){
        binding.toolbar2.setNavigationIcon(R.drawable.ic_back)
        binding.toolbar2.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_oneTimePinFragment_to_createUsernamePasswordFragment)
        }

        binding.pinView.doOnTextChanged { text, start, before, count ->
            if (text!!.length == 4) {
                lifecycleScope.launch {
                    hideSomeViews()
                    delay(2000)
                    findNavController().navigate(R.id.action_oneTimePinFragment_to_processCompletedFragment)
                }
            }
        }
    }

    private fun hideSomeViews(){
        binding.didnotReceiveOtpTextView.visibility = View.GONE
        binding.resendOtpTextView.visibility = View.GONE
        binding.authenticatingProgressBar.visibility = View.VISIBLE
        binding.authenticatingTextView.visibility = View.VISIBLE
    }

}