package com.example.fluentfyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fluentfyapp.Repository.ExerciseRepository

class ExerciseBasicViewModelFactory constructor(private val exerciseRepository: ExerciseRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ExerciseBasicViewModel::class.java)) {
            ExerciseBasicViewModel(this.exerciseRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}