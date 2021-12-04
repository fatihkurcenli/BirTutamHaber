package com.autumnsun.birtutamhaber.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.autumnsun.birtutamhaber.R
import com.autumnsun.birtutamhaber.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import nl.joery.animatedbottombar.AnimatedBottomBar


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var wantDisconnect: Boolean = false

    //private lateinit var appBarConfiguration: AppBarConfiguration
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    var seeAppRate = false

    //val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(R.id.homeFragment, R.id.writersFragment),
            binding.drawerLayout
        )
        setupActionBarWithNavController(
            navController = navController,
            configuration = appBarConfiguration
        )

        setupBottomNavMenu(navController)
        setupNavigationMenu(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setupNavigationMenu(navController: NavController) {
        binding.navView.setupWithNavController(navController)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        binding.bottomNavView.setOnTabSelectListener(object :
            AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                Log.d("select", lastIndex.toString())
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_nav_menu, menu)
        binding.bottomNavView.setupWithNavController(menu!!, navController)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        if (wantDisconnect) {
            super.onBackPressed()
        } else {
            if (binding.drawerLayout.isOpen) {
                binding.drawerLayout.close()
                return
            }
            Toast.makeText(this, "Çıkmak istiyorsanız tekrardan basınız.", Toast.LENGTH_SHORT)
                .show()
            wantDisconnect = true
            lifecycleScope.launch(Dispatchers.IO) {
                delay(2000L)
                wantDisconnect = false
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navController.handleDeepLink(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}