package com.example.kotlinmovieapp.presentation.browse

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.kotlinmovieapp.presentation.components.Filters

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Browse(
    navController: NavController,
    viewModel: BrowseViewModel
) {
    val state = viewModel.state.collectAsState()
    val gridState = rememberLazyGridState()
    val hasReachedLastItem by remember {
        derivedStateOf {
            gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ==
                    gridState.layoutInfo.totalItemsCount - 1
        }
    }

    if (hasReachedLastItem) {
        viewModel.getMovies(
            type = state.value.type.value,
            catalog = state.value.catalog.value,
            page = state.value.page + 1
        )
    }


       Column(
           modifier = Modifier
       ) {
           Filters(viewModel)
           val movies = state.value.movies

           LazyVerticalGrid(
               state = gridState,
               columns = GridCells.Fixed(3),
               contentPadding = PaddingValues(10.dp)
           ) {
               movies.forEachIndexed { _ , movie ->
                   item {
                       Card(
                           modifier = Modifier
                               .padding(10.dp)
                               .height(155.dp),
                           onClick = {
                               when (state.value.type.value) {
                                   "movie" -> navController.navigate("movie/${movie.id}")
                                   "anime" -> navController.navigate("anime/${movie.id}")
                                   "tv" -> navController.navigate("show/${movie.id}")
                               }
                           }
                       ) {
                           Image(
                               modifier = Modifier
                                   .fillMaxSize(),
                               contentScale = ContentScale.FillBounds,
                               painter = rememberAsyncImagePainter(
                                  movie.poster
                               ),
                               contentDescription = movie.title
                           )
                       }
                   }
               }
           }
       }
}




