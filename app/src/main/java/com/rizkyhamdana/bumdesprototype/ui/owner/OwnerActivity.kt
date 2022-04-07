package com.rizkyhamdana.bumdesprototype.ui.owner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.rizkyhamdana.bumdesprototype.R
import com.rizkyhamdana.bumdesprototype.databinding.ActivityOwnerBinding
import com.rizkyhamdana.bumdesprototype.ui.owner.home.OwnerHomeViewModel

class OwnerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOwnerBinding
    private lateinit var navController: NavController
    private lateinit var viewModel : OwnerHomeViewModel
    private lateinit var mAuth : FirebaseAuth
    var stand: String = " "


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOwnerBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[OwnerHomeViewModel::class.java]
        mAuth = FirebaseAuth.getInstance()
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHost.navController
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_home, R.id.navigation_message, R.id.navigation_profile, R.id.navigation_history))
        setupActionBarWithNavController(navController, appBarConfiguration)
        setupSmoothBottomMenu()
        viewModel.getAllOwner().observe(this){
            val emailLogin = mAuth.currentUser?.email
            for (i in it){
                if (i.email == emailLogin){
                    stand = i.stand
                }
            }
        }
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