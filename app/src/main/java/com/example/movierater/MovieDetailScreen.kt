package com.example.movierater.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.movierater.MovieDetail
import com.example.movierater.viewmodel.MainViewModel

@Composable
fun MovieDetailScreen(imdbID: String, viewModel: MainViewModel) {
    var movieDetail by remember { mutableStateOf<MovieDetail?>(null) }
    var rating by remember { mutableStateOf(5) }

    LaunchedEffect(imdbID) {
        movieDetail = viewModel.getMovieDetail(imdbID)
    }

    movieDetail?.let { detail ->
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(detail.Poster),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = detail.Title, style = MaterialTheme.typography.titleLarge)
            Text(text = detail.Genre, style = MaterialTheme.typography.bodyMedium)
            Text(text = detail.Plot, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Betyg: $rating")
            Slider(
                value = rating.toFloat(),
                onValueChange = { rating = it.toInt() },
                valueRange = 1f..10f,
                steps = 8
            )
            Button(onClick = {
                viewModel.saveRating(imdbID, detail.Title, rating)
            }) {
                Text("Spara betyg")
            }
        }
    } ?: run {
        CircularProgressIndicator()
    }
}
