package com.example.trainingintive.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.trainingintive.MyApplication
import com.example.trainingintive.R
import com.example.trainingintive.di.ViewModelFactory
import com.example.trainingintive.navigators.MainNavigator
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

// TODO You could create some base Activity class to keep there navigator attach/detach logic
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var navigator: MainNavigator

    val viewModel: MainViewModel by lazy { ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // TODO This part could be extracted to some private fun for readability
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
        //
        navigator.attachActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.detachActivityAndResetEvent()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                viewModel.logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
