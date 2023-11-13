package com.example.fluentfyapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.fluentfyapp.databinding.ActivityMainBinding
import com.example.fluentfyapp.viewmodel.ExerciseBasicViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        onTopButtonClick()
        onMidButtonClick()
        onBottomButtonClick()

    }

    private fun onBottomButtonClick() {
        val intent = Intent(this, AdvancedExercisesActivity::class.java)
        binding.buttonBottom.setOnClickListener {
            startActivity(intent)
        }
    }

    private fun onMidButtonClick() {
        val intent = Intent(this, IntermediaryExercisesActivity::class.java)
        binding.buttonMid.setOnClickListener {
            startActivity(intent)
        }
    }

    private fun onTopButtonClick() {
        val intent = Intent(this, BasicExercisesActivity::class.java)
        binding.buttonTop.setOnClickListener {
            startActivity(intent)
        }
    }
}