package com.example.kotlinmovieapp.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kotlinmovieapp.presentation.components.Carousel
import com.example.kotlinmovieapp.presentation.components.MovieRow




@Composable
fun Home(
    navController: NavController,
    viewModel: HomeViewModel
) {
    val state = viewModel.state.value
    Column (
    modifier = Modifier
        .padding(vertical = 20.dp)
        .verticalScroll(rememberScrollState())
    ) {

        Text(text = "In cinemas", modifier = Modifier
            .padding(10.dp))
        Carousel(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .height(209.5.dp),
            movies = state.trending?.results
        )
        state.movies?.let { MovieRow(data = it.results, type ="movie" , navController = navController) }
        state.shows?.let { MovieRow(data = it.results, type = "show", navController = navController) }

    }
}



