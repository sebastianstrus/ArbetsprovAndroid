package com.example.movierater.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movierater.Movie
import com.example.movierater.Screen
import com.example.movierater.viewmodel.MainViewModel

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    var searchQuery by remember { mutableStateOf("") }
    val searchResults by viewModel.searchResults.collectAsState()
    val ratedMovies by viewModel.ratedMovies.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadRatings()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Sök") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { viewModel.searchMovies(searchQuery) }) {
            Text("Sök")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Sökresultat", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(searchResults) { movie ->
                MovieItem(movie = movie, onClick = {
                    navController.navigate(Screen.Detail.createRoute(movie.imdbID))
                })
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Tidigare betygsatta", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            /*items(ratedMovies.entries.toList()) { entry ->
                Text("${entry.key}: Betyg ${entry.value}")
            }*/
            items(ratedMovies.entries.toList()) { entry ->
                val rated = entry.value
                Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                    Text(rated.title, modifier = Modifier.weight(1f))
                    Text("Betyg: ${rated.score}")
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Text(movie.Title, style = MaterialTheme.typography.bodyLarge)
        Text(movie.Year, style = MaterialTheme.typography.bodySmall)
    }
}
