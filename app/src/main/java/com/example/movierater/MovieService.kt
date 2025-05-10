package com.example.movierater.network

import com.example.movierater.Movie
import com.example.movierater.MovieDetail
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class MovieService {
    private val apiKey = "64298448"

    suspend fun searchMovies(query: String): List<Movie> = withContext(Dispatchers.IO) {
        val url = "https://www.omdbapi.com/?apikey=$apiKey&s=${query}"
        val response = URL(url).readText()
        val searchResponse = Gson().fromJson(response, SearchResponse::class.java)
        searchResponse.Search ?: emptyList()
    }

    suspend fun fetchMovieDetail(imdbID: String): MovieDetail? = withContext(Dispatchers.IO) {
        val url = "https://www.omdbapi.com/?apikey=$apiKey&i=$imdbID"
        val response = URL(url).readText()
        Gson().fromJson(response, MovieDetail::class.java)
    }

    data class SearchResponse(
        val Search: List<Movie>?
    )
}
