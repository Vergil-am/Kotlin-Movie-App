package com.example.kotlinmovieapp.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinmovieapp.domain.use_case.movies.get_movie.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
): ViewModel()  {
    private val _state = MutableStateFlow(MovieState())
    var state : StateFlow<MovieState> = _state

    fun updateId(id: Int, type: String) {
       _state.value = MovieState(id = id, movie = state.value.movie, show = state.value.show, isLoading = true, type = type)
    }

    fun getMovie(id: Int) {
            getMovieUseCase.getMovieDetails(id).onEach { movieDetailsDTO ->

                _state.value = MovieState(movie = movieDetailsDTO, show = null, isLoading = false)
            }.launchIn(viewModelScope)


    }
    fun getShow(id: Int) {
            getMovieUseCase.getShow(id).onEach { showDetailsDTO ->
                _state.value = MovieState(movie = null, show = showDetailsDTO, isLoading = false)
            }.launchIn(viewModelScope)
    }


}