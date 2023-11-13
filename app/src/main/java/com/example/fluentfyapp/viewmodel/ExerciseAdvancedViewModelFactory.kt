package com.example.fluentfyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fluentfyapp.Repository.ExerciseRepository

class ExerciseAdvancedViewModelFactory constructor(private val exerciseRepository: ExerciseRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ExerciseAdvancedViewModel::class.java)) {
            ExerciseAdvancedViewModel(this.exerciseRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}