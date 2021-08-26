package com.sefiso.matlatlefuneralpalourapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.*
import com.sefiso.matlatlefuneralpalourapplication.Fragments.signup.RequestQuoteFragment
import com.sefiso.matlatlefuneralpalourapplication.Fragments.home.ClaimsFragment
import com.sefiso.matlatlefuneralpalourapplication.Fragments.home.MessagesFragment
import com.sefiso.matlatlefuneralpalourapplication.Fragments.home.HomeScreenFragment
import com.sefiso.matlatlefuneralpalourapplication.databinding.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_MatlatleFuneralPalourApplication)
        setContentView(binding.root)

        replaceFragment(HomeScreenFragment())

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                //R.id.menuProfile -> replaceFragment(MessagesFragment())
                R.id.menuClaim -> replaceFragment(ClaimsFragment())
                R.id.menuHome ->replaceFragment(HomeScreenFragment())
                R.id.menuNotification -> replaceFragment(MessagesFragment())
                //R.id.moreItemsMenu -> binding.drawerLayout.openDrawer(GravityCompat.END)
                R.id.moreItemsMenu -> replaceFragment(RequestQuoteFragment())
            }
            true
        }

        //add badge on message icon
        val badge = binding.bottomNavigationView.getOrCreateBadge(R.id.menuNotification)
        badge.isVisible = true
        badge.number = 2
    }

    private fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}