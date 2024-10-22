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

class ShowAdapter(private val context: Context, private var shows: List<Show>) :
    RecyclerView.Adapter<ShowAdapter.ShowViewHolder>() {

    class ShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val showTitle: TextView = itemView.findViewById(R.id.showTitle)
        val showPoster: ImageView = itemView.findViewById(R.id.showPoster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_show, parent, false)
        return ShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        val show = shows[position]
        holder.showTitle.text = show.name

        val imageUrl = "https://image.tmdb.org/t/p/w500${show.posterPath}"
        Glide.with(context)
            .load(imageUrl)
            .apply(RequestOptions().transform(RoundedCorners(15)))
            .placeholder(R.drawable.placeholder)
            .into(holder.showPoster)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ShowDetailActivity::class.java)
            intent.putExtra("title", show.name)
            intent.putExtra("overview", show.overview)
            intent.putExtra("firstAirDate", show.firstAirDate)
            intent.putExtra("posterPath", show.posterPath)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return shows.size
    }


}