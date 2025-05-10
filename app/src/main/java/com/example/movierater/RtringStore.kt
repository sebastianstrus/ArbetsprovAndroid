package com.example.movierater.storage

import android.content.Context
import com.example.movierater.RatedMovie

class RatingStore(private val context: Context) {

    fun saveRating(movieId: String, title: String, rating: Int) {
        val prefs = context.getSharedPreferences("ratings", Context.MODE_PRIVATE)
        prefs.edit().putString(movieId, "$title||$rating").apply()
    }

    fun getAllRatings(): Map<String, RatedMovie> {
        val prefs = context.getSharedPreferences("ratings", Context.MODE_PRIVATE)
        return prefs.all.mapNotNull { (id, value) ->
            val parts = (value as? String)?.split("||")
            if (parts?.size == 2) {
                val title = parts[0]
                val score = parts[1].toIntOrNull()
                if (score != null) id to RatedMovie(title, score) else null
            } else null
        }.toMap()
    }
}