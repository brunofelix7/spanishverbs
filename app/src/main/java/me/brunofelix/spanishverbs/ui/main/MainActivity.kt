package me.brunofelix.spanishverbs.ui.main

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import me.brunofelix.spanishverbs.R
import me.brunofelix.spanishverbs.databinding.ActivityMainBinding
import me.brunofelix.spanishverbs.ui.account.AccountFragment
import me.brunofelix.spanishverbs.ui.favorites.FavoritesFragment
import me.brunofelix.spanishverbs.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    private fun initUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCurrentFragment(HomeFragment())
        toolbarSetup()
        navBarSetup()
    }

    private fun toolbarSetup() {
        binding.toolbar.inflateMenu(R.menu.action_menu)
        binding.toolbar.setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.action_search -> {
                    // TODO
                    true
                }
                R.id.action_settings -> {
                    // TODO
                    true
                }
                R.id.action_privacy -> {
                    // TODO
                    true
                }
                R.id.action_about -> {
                    // TODO
                    true
                }
                else -> false
            }
        }
    }

    private fun navBarSetup() {
        binding.navMenu.setOnItemSelectedListener {
            var selectedFragment: Fragment? = null

            when (it.itemId) {
                R.id.nav_home -> selectedFragment = HomeFragment()
                R.id.nav_favorites -> selectedFragment = FavoritesFragment()
                R.id.nav_account -> selectedFragment = AccountFragment()
            }
            setCurrentFragment(selectedFragment ?: HomeFragment())
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}