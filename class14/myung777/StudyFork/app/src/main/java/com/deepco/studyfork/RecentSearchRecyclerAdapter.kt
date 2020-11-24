package com.deepco.studyfork

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.deepco.studyfork.databinding.RecentSearchItemBinding

class RecentSearchRecyclerAdapter(
    private val onItemClick: (String) -> Unit
) :
    RecyclerView.Adapter<RecentSearchViewHolder>() {

    private var queryItem = ArrayList<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchViewHolder {
        val binding =
            DataBindingUtil.inflate<RecentSearchItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.recent_search_item,
                parent,
                false
            )
        return RecentSearchViewHolder(binding)
    }

    override fun getItemCount() = queryItem.count()

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
        holder.bind(this.queryItem[position])

        holder.itemView.setOnClickListener {
            this.queryItem[position].let(onItemClick)
        }
    }

    fun setItemList(queryItem: List<String>) {
        this.queryItem.clear()
        this.queryItem.addAll(queryItem)
        notifyDataSetChanged()
    }

    fun clear() {
        this.queryItem.clear()
        notifyDataSetChanged()
    }
}