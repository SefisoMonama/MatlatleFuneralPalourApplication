package com.sefiso.matlatlefuneralpalourapplication.viewmodels

import android.view.View
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sefiso.matlatlefuneralpalourapplication.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class LoginViewModel @ViewModelInject constructor(

):ViewModel() {

    private lateinit var auth: FirebaseAuth


    val showProgressBar = MutableLiveData(false)
    val navigateToHomeScreen = MutableLiveData(false)

    fun signInWithEmailAndPassword(email: String, password: String) {
        auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                   viewModelScope.launch {
                        showProgressBar.value = true
                        delay(1000)

                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser
                            showProgressBar.value = false
                            navigateToHomeScreen.value = true
                        } else {
                            // If sign in fails, display a message to the user.
                            viewModelScope.launch {
                                delay(1000)
                                showProgressBar.value = false
                                navigateToHomeScreen.value = false
                            }
                        }
                    }
                }
    }
}
