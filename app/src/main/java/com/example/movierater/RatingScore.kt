package com.example.movierater
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken

class RatingStore(private val context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("rating_store", Context.MODE_PRIVATE)
    private val gson = Gson()

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

    fun saveRating(movieId: String, title: String, rating: Int) {
        val prefs = context.getSharedPreferences("ratings", Context.MODE_PRIVATE)
        prefs.edit().putString(movieId, "$title||$rating").apply()
    }
}

data class RatedMovie(
    val title: String,
    val score: Int
)