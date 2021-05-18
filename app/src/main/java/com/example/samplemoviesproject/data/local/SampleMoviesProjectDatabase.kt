
package com.example.samplemoviesproject.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.samplemoviesproject.data.local.dao.MoviesDao
import com.example.samplemoviesproject.model.Movie

/**
 * Abstract SampleMoviesProject database.
 * It provides DAO [MoviesDao] by using method [getMoviesDao].
 */
@Database(
    entities = [Movie::class],
    version = DatabaseMigrations.DB_VERSION
)
abstract class SampleMoviesProjectDatabase : RoomDatabase() {

    /**
     * @return [MoviesDao] Sample Movies Data Access Object.
     */
    abstract fun getMoviesDao(): MoviesDao

    companion object {
        const val DB_NAME = "sample_movies_database"

        @Volatile
        private var INSTANCE: SampleMoviesProjectDatabase? = null

        fun getInstance(context: Context): SampleMoviesProjectDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SampleMoviesProjectDatabase::class.java,
                    DB_NAME
                ).addMigrations(*DatabaseMigrations.MIGRATIONS).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
