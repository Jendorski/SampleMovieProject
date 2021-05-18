
package com.example.samplemoviesproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.samplemoviesproject.model.Movie.Companion.TABLE_NAME

/**
 * Data class for Database entity and Serialization.
 */
@Entity(tableName = TABLE_NAME)
data class Movie(

    @PrimaryKey
    var id: Int? = 0,
    var title: String? = null,
    var original_title: String? = null,
    var overview: String? = null,
    var poster_path: String? = null
) {
    companion object {
        const val TABLE_NAME = "sample_movies_posts"
    }
}
