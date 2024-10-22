package com.example.flixsterplus2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class MovieAdapter(private val context: Context, private var movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieTitle: TextView = itemView.findViewById(R.id.movieTitle)
        val moviePoster: ImageView = itemView.findViewById(R.id.moviePoster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.movieTitle.text = movie.title

        val imageUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
        Glide.with(context)
            .load(imageUrl)
            .apply(RequestOptions().transform(RoundedCorners(15)))
            .placeholder(R.drawable.placeholder)
            .into(holder.moviePoster)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("title", movie.title)
            intent.putExtra("overview", movie.overview)
            intent.putExtra("releaseDate", movie.releaseDate)
            intent.putExtra("posterPath", movie.posterPath)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return movies.size
    }




}