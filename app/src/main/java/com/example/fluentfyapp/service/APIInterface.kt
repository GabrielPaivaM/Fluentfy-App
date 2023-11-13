package com.example.fluentfyapp.service

import com.example.fluentfyapp.model.RandomExercise
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIInterface {

    @GET("/random_exercise/1")
    fun getRandomExerciseBasic() : Call<RandomExercise>

    @GET("/random_exercise/2")
    fun getRandomExerciseIntermediary() : Call<RandomExercise>

    @GET("/random_exercise/3")
    fun getRandomExerciseAdvanced() : Call<RandomExercise>

    companion object {
        private const val BASE_URL = "https://frasesapi-y80l.onrender.com"

        private val apiInterface: APIInterface by lazy {

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(APIInterface::class.java)
        }

        fun getInstance(): APIInterface{
            return apiInterface
        }
    }
}