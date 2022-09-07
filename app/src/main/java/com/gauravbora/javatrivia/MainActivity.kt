package com.gauravbora.javatrivia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.gauravbora.javatrivia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var drawerLayout: DrawerLayout
    private  lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        drawerLayout=binding.drawerLayout
//        top back button
        val navController=this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)

        appBarConfiguration= AppBarConfiguration(navController.graph,drawerLayout)

        navController.addOnDestinationChangedListener{nc:NavController,nd:NavDestination,bundle:Bundle?->
            if(nd.id==nc.graph.startDestinationId){
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            }else{
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }

        }

        NavigationUI.setupWithNavController(binding.navView,navController)

    }


// continued
     override fun onSupportNavigateUp(): Boolean {
        val navController=this.findNavController(R.id.myNavHostFragment)
    return NavigationUI.navigateUp(navController,appBarConfiguration)
    }



}