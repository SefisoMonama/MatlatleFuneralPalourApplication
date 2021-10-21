package com.sefiso.matlatlefuneralpalourapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.core.view.GravityCompat
import androidx.fragment.app.*
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.sefiso.matlatlefuneralpalourapplication.Fragments.signup.RequestQuoteFragment
import com.sefiso.matlatlefuneralpalourapplication.Fragments.home.ClaimsFragment
import com.sefiso.matlatlefuneralpalourapplication.Fragments.home.MessagesFragment
import com.sefiso.matlatlefuneralpalourapplication.Fragments.home.HomeScreenFragment
import com.sefiso.matlatlefuneralpalourapplication.databinding.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_MatlatleFuneralPalourApplication)
        setContentView(binding.root)
    }
}