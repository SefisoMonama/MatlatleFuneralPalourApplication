package com.sefiso.matlatlefuneralpalourapplication.Fragments.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sefiso.matlatlefuneralpalourapplication.R
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentMoreBinding


class MoreFragment : BottomSheetDialogFragment() {

    private  lateinit var binding: FragmentMoreBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMoreBinding.inflate(layoutInflater)
        setupUi()
        return binding.root
    }

    private fun setupUi(){
        binding.exitAppTextView.setOnClickListener {
            dialog()
        }
        binding.exitAppImageView.setOnClickListener {
            dialog()
        }

        binding.loginSignUpImageView.setOnClickListener {
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle("LogOut")
                    .setCancelable(false)
                    .setMessage("You are about to log out.")
                    .setNegativeButton("Cancel"){dialog, _ -> dialog.dismiss()}
                    .setPositiveButton("LogOut"){dialog, _ -> findNavController().navigate(R.id.action_moreFragment_to_welcomeFragment)}
            dialog.create()
            dialog.show()
        }

        binding.loginSignUpTextView.setOnClickListener {
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle("Logout")
                    .setCancelable(false)
                    .setMessage("Press the logout button to continue logging out.")
                    .setNegativeButton("Cancel"){dialog, _ -> dialog.dismiss()}
                    .setPositiveButton("LogOut"){dialog, _ -> findNavController().navigate(R.id.action_moreFragment_to_welcomeFragment)}
            dialog.create()
            dialog.show()
        }

    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
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