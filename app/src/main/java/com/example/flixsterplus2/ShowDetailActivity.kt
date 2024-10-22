package com.example.flixsterplus2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ShowDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_detail)

        // Intent에서 데이터 가져오기
        val showTitle = intent.getStringExtra("title")
        val showOverview = intent.getStringExtra("overview")
        val showDate = intent.getStringExtra("firstAirDate")
        val posterPath = intent.getStringExtra("posterPath")

        // View에 데이터 설정
        val titleTextView: TextView = findViewById(R.id.showTitle)
        val overviewTextView: TextView = findViewById(R.id.showOverview)
        val dateTextView: TextView = findViewById(R.id.showDate)
        val posterImageView: ImageView = findViewById(R.id.showImage)

        titleTextView.text = showTitle
        overviewTextView.text = showOverview
        dateTextView.text = showDate

        // 포스터 이미지 로드
        val imageUrl = "https://image.tmdb.org/t/p/w500$posterPath"
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .into(posterImageView)
    }
}
