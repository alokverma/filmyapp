package com.akki.filmyapp.home.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.akki.filmyapp.databinding.HomeActivityBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: HomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}