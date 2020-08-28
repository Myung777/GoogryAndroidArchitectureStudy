package com.world.tree.architecturestudy

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dwl.study_library.model.Movie
import com.world.tree.architecturestudy.view.MovieAdapter

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, url:String) {
    Glide.with(imageView).load(url)
        .centerCrop()
        .into(imageView)
}

@BindingAdapter("bindData")
fun bindData(recyclerView: RecyclerView, data : List<Movie.Item>) {
    val adapter = recyclerView.adapter as? MovieAdapter
    adapter?.let {
        it.clearData()
        it.addData(data)
    }

}