package com.example.movierater.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.movierater.Movie
import com.example.movierater.MovieDetail
import com.example.movierater.RatedMovie
import com.example.movierater.network.MovieService
import com.example.movierater.storage.RatingStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val movieService = MovieService()
    private val ratingStore = RatingStore(application)

    private val _searchResults = MutableStateFlow<List<Movie>>(emptyList())
    val searchResults: StateFlow<List<Movie>> = _searchResults

    private val _ratedMovies = MutableStateFlow<Map<String, RatedMovie>>(emptyMap())
    val ratedMovies: StateFlow<Map<String, RatedMovie>> = _ratedMovies

    fun saveRating(movieId: String, title: String, rating: Int) {
        viewModelScope.launch {
            ratingStore.saveRating(movieId, title, rating)
            loadRatings()
        }
    }

    fun searchMovies(query: String) {
        viewModelScope.launch {
            val results = movieService.searchMovies(query)
            _searchResults.value = results
        }
    }

    fun loadRatings() {
        viewModelScope.launch {
            _ratedMovies.value = ratingStore.getAllRatings()
        }
    }

    suspend fun getMovieDetail(imdbID: String): MovieDetail? {
        return movieService.fetchMovieDetail(imdbID)
    }
}
