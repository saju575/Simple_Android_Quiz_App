package com.example.simplequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.simplequiz.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        drawerLayout = binding.drawerLayout


        //setupActionBarWithNavController(navController,drawerLayout)
        //navigationView.setupWithNavController(navController)
        //navigation up
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        //val navController=this.findNavController(R.id.fragmentContainerView)
        //NavigationUI.setupActionBarWithNavController(this,navController)
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, drawerLayout)
        //NavigationUI.setupWithNavController(binding.navView,navController)
        binding.navView.setupWithNavController(navController)

        // prevent nav gesture if not on start destination
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (controller.graph.startDestination == destination.id) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        //val navController=findNavController(R.id.fragmentContainerView)
        //return navController.navigateUp()||super.onSupportNavigateUp()
        //return NavigationUI.navigateUp(drawerLayout,navController)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    }
}