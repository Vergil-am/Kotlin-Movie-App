package com.example.kotlinmovieapp.presentation.browse

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.kotlinmovieapp.presentation.navgraph.Route
import com.example.kotlinmovieapp.util.Constants

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
    var opened by remember {
        mutableStateOf("")
    }
   Scaffold (
       topBar = {
           TopAppBar(
               title = { Text(text = "LOGO")},
               actions = {
                   IconButton(onClick = {
                       navController.navigate(Route.Search.route)
                   }) {
                       Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search icon")
                   }
               }
               )
       }
   ) { paddingValues ->
       Column(
           modifier = Modifier
               .padding(paddingValues)
       ) {
           Row {
               Box(modifier = Modifier) {
                   OutlinedButton(onClick = { opened = "type" }) {
                       Text(text = state.value.type.title)
                       Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
                   }
                   DropdownMenu(
                       expanded = opened == "type",
                       onDismissRequest = {
                           opened = ""
                       }) {
                       Types.forEach {type ->
                           DropdownMenuItem(text = {
                               Text(text = type.title)
                           }, onClick = {
                               state.value.type = type
                               opened = ""
                               viewModel.getMovies(type = type.value, catalog = "popular", 1)
                               viewModel.getGenres(type = type.value)
                           })
                       }


                   }
               }
               Box(modifier = Modifier) {
                   OutlinedButton(onClick = { opened = "catalog"}) {
                       Text(text = state.value.catalog.title)
                       Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
                   }
                   DropdownMenu(
                       expanded = opened == "catalog",
                       onDismissRequest = {
                           opened = ""
                       }) {
                       state.value.type.catalog.forEach {item ->
                           DropdownMenuItem(text = {
                               Text(text = item.title)
                           }, onClick = {
                               state.value.catalog = item
                               opened = ""
                               viewModel.getMovies(state.value.type.value ,item.value, page = 1)
                           })

                       }
                   }

               }
               Box(modifier = Modifier) {
                   OutlinedButton(onClick = { opened = "genres" }) {
                       Text(text = state.value.genre?.name ?: "all")
                       Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
                   }
                   DropdownMenu(
                       expanded = opened == "genres",
                       onDismissRequest = {
                           opened = ""
                       }) {
                       state.value.genres?.genres?.forEach { genre ->
                           DropdownMenuItem(text = {
                               Text(text = genre.name)
                           }, onClick = {
                               state.value.genre = genre
                               opened = ""
                           })
                       }


                   }
               }

           }
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
                               if (
                                   state.value.type.value == "movies"
                               ) {

                                   navController.navigate("movie/${movie.id}")
                               } else {
                                   navController.navigate("show/${movie.id}")
                               }
                           }
                       ) {
                           Image(
                               modifier = Modifier
                                   .fillMaxSize(),

                               painter = rememberAsyncImagePainter(
                                   "${Constants.IMAGE_BASE_URL}/w200${movie.poster_path}"),
                               contentDescription = movie.title
                           )
                       }
                   }
               }
           }
       }


       }
   }




