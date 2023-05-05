package com.example.myrestapiapp.module.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myrestapiapp.model.response.Movie
import com.example.myrestapiapp.module.network.NetworkService


@Composable
fun MainScreen(service: NetworkService) {

    val repository = MainRepository(service)
    val viewModel = MainViewModel(repository)

    viewModel.getMovieList()

    val movies = viewModel.movies.value

    if(movies != null) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            items(movies) { item ->
                MovieCard(movie = item)
            }
        }
    }
}

@Composable
fun MovieCard(movie: Movie) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(10.dp)
            .clickable {

            },
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.primaryVariant)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = movie.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = movie.overview,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 2
                )
            }

            AsyncImage(
                model = String.format("https://image.tmdb.org/t/p/w500/%s", movie.image),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
            )
        }
    }
}