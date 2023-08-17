package com.example.footballmatchresults.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.footballmatchresults.APP_MAIN_ACTIVITY
import com.example.footballmatchresults.R
import com.example.footballmatchresults.databinding.ActivityMainBinding
import com.example.footballmatchresults.utilits.replaceFragmentMainActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP_MAIN_ACTIVITY = this
    }

    override fun onStart() {
        super.onStart()
        replaceFragmentMainActivity(HomeFragment())
    }
}