package com.kilani.fizzbuzz.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.kilani.fizzbuzz.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.main_toolbar))
        val hostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        hostFragment?.findNavController()?.let { setupActionBarWithNavController(it) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                val hostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                hostFragment?.findNavController()?.popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}