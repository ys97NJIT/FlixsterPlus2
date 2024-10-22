package com.example.flixsterplus2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    private lateinit var rvMovies: RecyclerView
    private lateinit var movies: MutableList<Movie>
    private lateinit var movieAdapter: MovieAdapter

    private lateinit var rvShows: RecyclerView
    private lateinit var shows: MutableList<Show>
    private lateinit var showAdapter: ShowAdapter

    companion object {
        private const val TAG = "MainActivity"
        private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
        private const val MOVIES_API_URL = "https://api.themoviedb.org/3/movie/top_rated?api_key=$API_KEY"
        private const val SHOWS_API_URL = "https://api.themoviedb.org/3/tv/top_rated?api_key=$API_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Movies RecyclerView setup
        rvMovies = findViewById(R.id.rvMovies)
        movies = mutableListOf()
        movieAdapter = MovieAdapter(this, movies)
        rvMovies.adapter = movieAdapter
        rvMovies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Shows RecyclerView setup
        rvShows = findViewById(R.id.rvShows)
        shows = mutableListOf()
        showAdapter = ShowAdapter(this, shows)
        rvShows.adapter = showAdapter
        rvShows.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Fetch data
        fetchMovies()
        fetchShows()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun fetchMovies() {
        val client = AsyncHttpClient()
        client.get(MOVIES_API_URL, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.d(TAG, "fetchMovies onSuccess")
                val results = json.jsonObject.getJSONArray("results")
                val moviesRawJSON = results.toString()
                val gson = Gson()
                val arrayMovieType = object : TypeToken<List<Movie>>() {}.type
                val moviesFromApi: List<Movie> = gson.fromJson(moviesRawJSON, arrayMovieType)
                movies.addAll(moviesFromApi)

                Log.d(TAG, "Moview fetched: $moviesFromApi")

                runOnUiThread {
                    movieAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "fetchMovies onFailure: $statusCode", throwable)
            }
        })
    }

    private fun fetchShows() {
        val client = AsyncHttpClient()
        client.get(SHOWS_API_URL, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.d(TAG, "fetchShows onSuccess")
                val results = json.jsonObject.getJSONArray("results")
                val showsRawJSON = results.toString()
                val gson = Gson()
                val arrayShowType = object : TypeToken<List<Show>>() {}.type
                val showsFromApi: List<Show> = gson.fromJson(showsRawJSON, arrayShowType)
                shows.addAll(showsFromApi)

                Log.d(TAG, "Shows fetched: $showsFromApi")

                runOnUiThread {
                    showAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "fetchShows onFailure: $statusCode", throwable)
            }
        })
    }
}
