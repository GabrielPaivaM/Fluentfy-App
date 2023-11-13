package com.example.fluentfyapp.view

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fluentfyapp.R
import com.example.fluentfyapp.Repository.ExerciseRepository
import com.example.fluentfyapp.databinding.ActivityBasicExercisesBinding
import com.example.fluentfyapp.service.APIInterface
import com.example.fluentfyapp.viewmodel.ExerciseBasicViewModel
import com.example.fluentfyapp.viewmodel.ExerciseBasicViewModelFactory

class BasicExercisesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBasicExercisesBinding
    private lateinit var viewModel: ExerciseBasicViewModel
    private val retrofitService = APIInterface.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasicExercisesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, ExerciseBasicViewModelFactory(ExerciseRepository(retrofitService))).get(
            ExerciseBasicViewModel::class.java
        )
    }

    override fun onStart() {
        super.onStart()
        initExercise()
        onCloseButtonClick()

    }

    override fun onResume() {
        super.onResume()
        viewModel.getExercise()
    }

    private fun generateRandomNumber(): Int {
        return (0..100).random()
    }

    @SuppressLint("ResourceAsColor")
    private fun initExercise() {
        viewModel.exercise.observe(this, Observer { exercise ->
            binding.progressBar.visibility = View.INVISIBLE
            binding.exerciseText.text = exercise.frase_ingles
            val buttonTop = binding.buttonTop
            val buttonMid = binding.buttonMid
            val buttonBottom = binding.buttonBottom

            buttonTop.isEnabled = true
            buttonMid.isEnabled = true
            buttonBottom.isEnabled = true

            buttonTop.setBackgroundColor(resources.getColor(R.color.greybutton))
            buttonMid.setBackgroundColor(resources.getColor(R.color.greybutton))
            buttonBottom.setBackgroundColor(resources.getColor(R.color.greybutton))

            val num = generateRandomNumber()

            if (num <= 30) {
                buttonTop.text = exercise.traducao
                buttonMid.text = exercise.traducao2
                buttonBottom.text = exercise.traducao3
            }
            if (num in 31..60){
                buttonTop.text = exercise.traducao2
                buttonMid.text = exercise.traducao
                buttonBottom.text = exercise.traducao3
            }
            if (num > 60) {
                buttonTop.text = exercise.traducao3
                buttonMid.text = exercise.traducao2
                buttonBottom.text = exercise.traducao
            }

            buttonTop.setOnClickListener {
                buttonMid.isEnabled = false
                buttonBottom.isEnabled = false
                if (buttonTop.text.equals(exercise.traducao)){
                    buttonTop.setBackgroundColor(resources.getColor(R.color.green))
                    playCorrectAnswerSound()
                    getNewExerciseWithDelay()
                } else {
                    buttonTop.setBackgroundColor(resources.getColor(R.color.red))
                    vibrate()
                    getNewExerciseWithDelay()
                }
            }

            buttonMid.setOnClickListener {
                buttonTop.isEnabled = false
                buttonBottom.isEnabled = false
                if (buttonMid.text.equals(exercise.traducao)){
                    buttonMid.setBackgroundColor(resources.getColor(R.color.green))
                    playCorrectAnswerSound()
                    getNewExerciseWithDelay()
                } else {
                    buttonMid.setBackgroundColor(resources.getColor(R.color.red))
                    vibrate()
                    getNewExerciseWithDelay()
                }
            }

            buttonBottom.setOnClickListener {
                buttonTop.isEnabled = false
                buttonMid.isEnabled = false
                if (buttonBottom.text.equals(exercise.traducao)){
                    buttonBottom.setBackgroundColor(resources.getColor(R.color.green))
                    playCorrectAnswerSound()
                    getNewExerciseWithDelay()
                } else {
                    buttonBottom.setBackgroundColor(resources.getColor(R.color.red))
                    vibrate()
                    getNewExerciseWithDelay()
                }
            }
        })

        viewModel.errorMessage.observe(this, Observer { errorMessage->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        })

        viewModel.currencyQuestion.observe(this, Observer { currencyQuestion->
            binding.numExercise.text = "Questão $currencyQuestion"
        })
    }

    private fun onCloseButtonClick(){
        binding.buttonClose.setOnClickListener {
            finish()
        }
    }

    private fun getNewExerciseWithDelay(){
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.getExercise()
        }, 250)
    }

    private fun playCorrectAnswerSound(){
        val correctAnswerMediaPlayer = MediaPlayer.create(this, R.raw.correctansweraudio)

        if (correctAnswerMediaPlayer.isPlaying){
            correctAnswerMediaPlayer.stop()
        }
        correctAnswerMediaPlayer.start()
    }

    private fun vibrate(){
        val vibrate = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        if (Build.VERSION.SDK_INT > 26){
            vibrate.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrate.vibrate(100)
        }
    }
}