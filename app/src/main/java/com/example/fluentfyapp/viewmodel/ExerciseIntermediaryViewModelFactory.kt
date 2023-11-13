package com.example.fluentfyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fluentfyapp.Repository.ExerciseRepository

class ExerciseIntermediaryViewModelFactory constructor(private val exerciseRepository: ExerciseRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ExerciseIntermediaryViewModel::class.java)) {
            ExerciseIntermediaryViewModel(this.exerciseRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}