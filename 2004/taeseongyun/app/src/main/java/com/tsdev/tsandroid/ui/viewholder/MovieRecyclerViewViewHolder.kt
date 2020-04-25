package com.tsdev.tsandroid.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.tsdev.tsandroid.data.Item
import com.tsdev.tsandroid.R
import com.tsdev.tsandroid.base.BaseRecyclerViewHolder
import com.tsdev.tsandroid.ext.showToast
import com.tsdev.tsandroid.util.htmlConvert
import kotlinx.android.synthetic.main.movie_recycler_item.view.*

class MovieRecyclerViewViewHolder(onClickListenerEvent: OnClickDelegate, parent: ViewGroup) :
    BaseRecyclerViewHolder<Item>(
        LayoutInflater.from(parent.context).inflate(R.layout.movie_recycler_item, parent, false)
    ) {
    init {
        itemView.setOnClickListener {
            adapterPosition.takeIf { adapterPosition > -1 }?.let {
                onClickListenerEvent.onClickEventListener(it)
            } ?: parent.context.showToast(Toast.LENGTH_LONG)
        }
    }

    interface OnClickDelegate {
        fun onClickEventListener(position: Int)
    }

    override fun onBindViewHolder(item: Item) {
        itemView.movie_actor.text = item.actor

        itemView.movie_img.loadMovieImage(item.image)

        itemView.movie_director.text = item.director
        itemView.movie_sub_title.text = item.subtitle
        itemView.movie_rating.text = item.userRating
        itemView.movie_name.text = item.title
    }
}