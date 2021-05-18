package com.example.samplemoviesproject

import androidx.room.Room.inMemoryDatabaseBuilder
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.samplemoviesproject.data.local.SampleMoviesProjectDatabase
import com.example.samplemoviesproject.model.Movie
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostsDaoTest {

    private lateinit var mDatabase: SampleMoviesProjectDatabase

    @Before
    fun init() {
        mDatabase = inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                SampleMoviesProjectDatabase::class.java
        ).build()
    }

    @Test
    @Throws(InterruptedException::class)
    fun insert_and_select_posts() = runBlocking {
        val movies = listOf(
                Movie(1, "Test 1", "Test 1", "Test 1"),
                Movie(2, "Test 2", "Test 2", "Test 3")
        )

        mDatabase.getMoviesDao().insertMovies(movies)

        val dbPosts = mDatabase.getMoviesDao().getAllMovies().toList()[0]

        assertThat(dbPosts, equalTo(movies))
    }

    @Test
    @Throws(InterruptedException::class)
    fun select_post_by_id() = runBlocking {
        val posts = listOf(
                Movie(1, "Test 1", "Test 1", "Test 1"),
                Movie(2, "Test 2", "Test 2", "Test 3")
        )

        mDatabase.getMoviesDao().insertMovies(posts)

        var dbPost = mDatabase.getMoviesDao().getMovieById(1)
        assertThat(dbPost.toList()[0], equalTo(posts[0]))

        dbPost = mDatabase.getMoviesDao().getMovieById(1)
        assertThat(dbPost.toList()[1], equalTo(posts[1]))
    }

    @After
    fun cleanup() {
        mDatabase.close()
    }
}
