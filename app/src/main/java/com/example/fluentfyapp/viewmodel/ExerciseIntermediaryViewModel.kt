package com.example.fluentfyapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fluentfyapp.Repository.ExerciseRepository
import com.example.fluentfyapp.model.RandomExercise
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExerciseIntermediaryViewModel constructor(private val exerciseRepository: ExerciseRepository): ViewModel() {
    var exercise = MutableLiveData<RandomExercise>()
    val errorMessage = MutableLiveData<String>()
    var questionNumber = 1
    var currencyQuestion = MutableLiveData<Int>()

    fun getExercise(){

        exerciseRepository.getRandomExerciseIntermediary().enqueue(object : Callback<RandomExercise> {
            override fun onResponse(
                call: Call<RandomExercise>,
                response: Response<RandomExercise>
            ) {
                exercise.postValue(response.body())
                currencyQuestion.postValue(questionNumber++)
            }

            override fun onFailure(call: Call<RandomExercise>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}