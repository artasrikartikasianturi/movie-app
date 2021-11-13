package com.example.movieapp.ui.components

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.core.kit.BaseActivity
import com.example.movieapp.MyApplication
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi

 class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {

     private lateinit var navController: NavController

     @ExperimentalCoroutinesApi
     override fun ActivityMainBinding.onCreate(savedInstanceState: Bundle?) {
         (application as MyApplication).appComponent.inject(this@MainActivity)
         val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
         navController = navHostFragment.navController
         binding.navView.setupWithNavController(navController)
     }

     override fun observeViewModel() {}
}