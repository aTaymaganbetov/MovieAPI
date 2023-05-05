package com.example.myrestapiapp.module.main

import com.example.myrestapiapp.module.network.NetworkService

class MainRepository(
    val networkService: NetworkService
)
{
    suspend fun getMovieList() = networkService.getMovieList()
}