package com.geeks.homecirclelndicator.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.geeks.homecirclelndicator.R

class MainActivity : AppCompatActivity() {

    private lateinit var  sharedPreferences: SharedPreferences

    private val PREFS_KEY = "onboarding_show"

    private val PREFS_NAME = "MyPrefs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_host)

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        if (isOnBoardingShown()){
            navGraph.setStartDestination(R.id.secondFragment)
        } else {
            navGraph.setStartDestination(R.id.onBoardFragment)
        }

        navController.setGraph(navGraph, intent.extras)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun isOnBoardingShown(): Boolean{
        return sharedPreferences.getBoolean(PREFS_KEY, false)
    }

    fun setOnBoardingShown(isShown: Boolean){
        sharedPreferences.edit().putBoolean(PREFS_KEY, isShown).apply()
    }



}