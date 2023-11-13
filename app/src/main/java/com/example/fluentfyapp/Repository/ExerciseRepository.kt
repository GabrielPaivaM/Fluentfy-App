package com.example.fluentfyapp.Repository

import com.example.fluentfyapp.model.RandomExercise
import com.example.fluentfyapp.service.APIInterface
import retrofit2.Call

class ExerciseRepository constructor(private val apiInterface: APIInterface) {

    fun getRandomExerciseBasic(): Call<RandomExercise> {
        return apiInterface.getRandomExerciseBasic()
    }

    fun getRandomExerciseIntermediary(): Call<RandomExercise>{
        return apiInterface.getRandomExerciseIntermediary()
    }

    fun getRandomExerciseAdvanced(): Call<RandomExercise>{
        return apiInterface.getRandomExerciseAdvanced()
    }
}