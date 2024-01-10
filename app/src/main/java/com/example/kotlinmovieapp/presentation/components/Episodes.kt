package com.example.kotlinmovieapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.kotlinmovieapp.R
import com.example.kotlinmovieapp.data.local.entities.WatchListMedia
import com.example.kotlinmovieapp.domain.model.Season
import com.example.kotlinmovieapp.presentation.details.DetailsViewModel
import com.example.kotlinmovieapp.util.Constants
import java.net.URLEncoder

data class SelectedEpisode(
    val season: Int,
    val episode: Int
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Episodes(
    season: Season, viewModel: DetailsViewModel, id: Int, navController: NavController
) {
    val state = viewModel.state.collectAsState().value

    var opened by remember {
        mutableStateOf(false)
    }
    var selected by remember {
        mutableStateOf(SelectedEpisode(season = 1, episode = 1))
    }
    LaunchedEffect(key1 = season, key2 = id) {
        viewModel.getSeason(id, season.season_number)
    }


    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {

        state.season?.episodes?.forEach { episode ->
//            val url =
//
            Card(
                onClick = {
                    viewModel.addToWatchList(
                        WatchListMedia(
                            id = id.toString(),
                            list = "Watching",
                            season = season.season_number,
                            episode = episode.episode_number,
                            poster = state.media?.poster ?: season.poster_path,
                            title = state.media?.title ?: season.name,
                            type = "show"
                        )
                    )
                    opened = true
                    selected = SelectedEpisode(season = season.season_number, episode = episode.episode_number)
//                    navController.navigate("web-view/$url")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(10.dp),
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Row {
                        Box(
                            modifier = Modifier.width(180.dp)
                        ) {
                            Image(
                                alignment = Alignment.TopStart,
                                modifier = Modifier.fillMaxSize(),
                                painter = rememberAsyncImagePainter(
                                    model = "${Constants.IMAGE_BASE_URL}/w200${episode.still_path}"
                                ),
                                contentDescription = "Episode ${episode.episode_number}"
                            )
                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .background(Color.Black)

                            ) {
                                Text(text = "EP ${episode.episode_number}")
                            }
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp),
                            verticalArrangement = Arrangement.SpaceBetween,
                        ) {
                            episode.name?.let { Text(text = it) }
                            episode.air_date?.let {
                                Text(
                                    text = it,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.End

                                )
                            }
                            Text(
                                text = "${episode.runtime} min",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.End
                            )

                        }
                    }
                    val progressSeason = state.watchList?.season
                    val progressEpisode = state.watchList?.episode
                    if (progressEpisode != null && progressSeason != null) {
                        if (progressEpisode >= season.season_number && progressEpisode >= episode.episode_number) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black.copy(alpha = 0.2f))
                            ) {
                                Box(modifier = Modifier.width(180.dp)) {
                                    Image(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .align(Alignment.Center),
                                        painter = painterResource(
                                            id = R.drawable.visibility_white_64dp
                                        ),
                                        contentDescription = "",
                                        alpha = 0.8f
                                    )
                                }
                            }

                        }
                    }

                }
            }

        }
    }
    if (opened) {
        ModalBottomSheet(
            onDismissRequest = { opened = false }, sheetState = rememberModalBottomSheetState()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "SELECT SOURCE",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )
                Source(
                    source = "FHD 1080p",
                    link = URLEncoder.encode("${Constants.VIDSRC_FHD}/tv/$id/${selected.season}/${selected.episode}?ds_langs=en,fr,ar"
                    ),
                    navController = navController
                )
                Source(
                    source = "Multi",
                    link = URLEncoder.encode("${Constants.VIDSRC_MULTI}/tv/$id/${selected.season}/${selected.episode}"
                    ),
                    navController = navController
                )

            }
        }
    }
}

