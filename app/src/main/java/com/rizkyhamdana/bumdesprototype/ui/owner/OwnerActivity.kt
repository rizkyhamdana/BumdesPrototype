package com.rizkyhamdana.bumdesprototype.ui.owner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.rizkyhamdana.bumdesprototype.R
import com.rizkyhamdana.bumdesprototype.databinding.ActivityOwnerBinding

class OwnerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOwnerBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOwnerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHost.navController
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_home, R.id.navigation_message, R.id.navigation_profile, R.id.navigation_history))
        setupActionBarWithNavController(navController, appBarConfiguration)
        setupSmoothBottomMenu()
    }

    private fun setupSmoothBottomMenu() {
        val popupMenu = PopupMenu(this, null)
        popupMenu.inflate(R.menu.bottom_nav_menu)
        val menu = popupMenu.menu
        binding.navView.setupWithNavController(menu, navController)
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}