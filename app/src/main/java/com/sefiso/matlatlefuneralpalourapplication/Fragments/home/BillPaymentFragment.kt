package com.sefiso.matlatlefuneralpalourapplication.Fragments.home

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sefiso.matlatlefuneralpalourapplication.R
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentBillPaymentBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class BillPaymentFragment : Fragment() {

    private lateinit var binding: FragmentBillPaymentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBillPaymentBinding.inflate(layoutInflater)
        setupUi()
        return binding.root
    }

    private fun setupUi() {

        /**navigate back to home fragment*/
        //when traditional back button pressed move to home fragment
        val callback2 = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.homeScreenFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback2)

        //when back button pressed move to home fragment
        binding.billPaymentToolbar.setNavigationIcon(R.drawable.ic_back)
        binding.billPaymentToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_billPaymentFragment_to_homeScreenFragment)
        }

        //when pay button pressed show progress bar and dialog box after
        binding.payButton.setOnClickListener {
            lifecycleScope.launch {
                binding.loadPaymentProgressBar.visibility = View.VISIBLE
                delay(5000)
                binding.loadPaymentProgressBar.visibility = View.VISIBLE
                dialog1()
            }
        }
    }

    private fun dialog1() {
        val dialog = AlertDialog.Builder(context)
        dialog.setMessage("You're about to make a payment of R255.00 to Matlatle.cc. Press PAY to continue!")
            .setCancelable(false)
            .setPositiveButton("PAY") { _, _ -> dialog2() }
            .setNegativeButton("CANCEL") { dialog, _ -> dialog.dismiss() }
            .setTitle("PAYMENT")
        dialog.create()
        dialog.show()
    }

    private fun dialog2() {
        val dialog = AlertDialog.Builder(context)
        dialog.setMessage("Payment of R255.00 to Matlatle.cc was successful")
            .setCancelable(false)
            .setPositiveButton("THANK YOU!") { dialog, _ ->
                dialog.dismiss()
                binding.loadPaymentProgressBar.visibility = View.GONE
            }
            .setTitle("PAYMENT SUCCESSFUL")
        dialog.create()
        dialog.show()
    }
}