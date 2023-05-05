package com.example.myrestapiapp.module.network

import com.example.myrestapiapp.model.response.Movie
import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {
    @GET("/discover/movie")
    suspend fun getMovieList(): Response<List<Movie>>

    @GET("/movie/{movie_id}")
    suspend fun getMovieItem(): Response<Movie>
}