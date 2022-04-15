package com.rizkyhamdana.bumdesprototype.ui.user

import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.rizkyhamdana.bumdesprototype.R
import com.rizkyhamdana.bumdesprototype.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawLayout()
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

    private fun isNetworkAvailable(): Boolean{
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)

        return (capabilities != null && capabilities.hasCapability(NET_CAPABILITY_INTERNET))
    }

    private fun drawLayout(){
        if (isNetworkAvailable()){
            binding.mainLayout.visibility = View.VISIBLE
            binding.layoutNoInteret.root.visibility = View.GONE
            setSupportActionBar(binding.toolBar)
            val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
            navController = navHost.navController
            val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_home, R.id.navigation_message, R.id.navigation_profile, R.id.navigation_history))
            setupActionBarWithNavController(navController, appBarConfiguration)
            setupSmoothBottomMenu()
        }else{
            binding.mainLayout.visibility = View.GONE
            binding.layoutNoInteret.root.visibility = View.VISIBLE
            binding.layoutNoInteret.btnTryAgain.setOnClickListener {
                drawLayout()
            }
        }
    }

}