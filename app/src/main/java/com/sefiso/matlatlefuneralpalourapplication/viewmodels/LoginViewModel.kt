package com.sefiso.matlatlefuneralpalourapplication.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(

) : ViewModel() {

    private lateinit var auth: FirebaseAuth

    val showProgressBar = MutableLiveData<Boolean?>()
    val loginSuccessful = MutableLiveData<Boolean?>()

    fun signInWithEmailAndPassword(email: String, password: String) {
        auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                viewModelScope.launch {
                    showProgressBar(true)
                    delay(3000)
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        showProgressBar(false)
                        loginSuccessfully(true)
                    } else {
                        showProgressBar(false)
                    }
                }
            }
    }

    private fun showProgressBar(show: Boolean) {
        showProgressBar.value = show
    }

    private fun loginSuccessfully(success: Boolean) {
        loginSuccessful.value = success
    }
}
