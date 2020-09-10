package com.example.aas.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aas.R
import com.example.aas.data.model.Movie
import com.example.aas.utils.glideCenterCrop
import com.example.aas.utils.toHtml
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
) {

    private val movieImage = itemView.img_movie
    private val movieTitle = itemView.tv_title
    private val movieSubTitle = itemView.tv_subtitle
    private val movieActor = itemView.tv_actor
    private val movieRating = itemView.tv_rating

    fun bind(movie: Movie) {
        movieImage.glideCenterCrop(movie.image)
        movieTitle.text = movie.title.toHtml()
        movieSubTitle.text = movie.subtitle.toHtml()
        movieActor.text = movie.actor.toHtml()
        movieRating.text = movie.userRating.toHtml()
    }
}