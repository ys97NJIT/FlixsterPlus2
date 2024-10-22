package com.example.flixsterplus2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        // Intent에서 데이터 가져오기
        val movieTitle = intent.getStringExtra("title")
        val movieOverview = intent.getStringExtra("overview")
        val movieDate = intent.getStringExtra("releaseDate")
        val posterPath = intent.getStringExtra("posterPath")

        // View에 데이터 설정
        val titleTextView: TextView = findViewById(R.id.movieTitle)
        val overviewTextView: TextView = findViewById(R.id.movieOverview)
        val dateTextView: TextView = findViewById(R.id.movieDate)
        val posterImageView: ImageView = findViewById(R.id.movieImage)

        titleTextView.text = movieTitle
        overviewTextView.text = movieOverview
        dateTextView.text = movieDate

        // 포스터 이미지 로드
        val imageUrl = "https://image.tmdb.org/t/p/w500$posterPath"
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .into(posterImageView)
    }
}
