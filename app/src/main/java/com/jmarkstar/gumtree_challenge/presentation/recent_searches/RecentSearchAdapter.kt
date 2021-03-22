package com.jmarkstar.gumtree_challenge.presentation.recent_searches

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jmarkstar.gumtree_challenge.databinding.ViewRecentSearchBinding
import com.jmarkstar.gumtree_challenge.domain.models.RecentSearchModel

class RecentSearchAdapter : RecyclerView.Adapter<RecentSearchAdapter.RecentSearchViewHolder>() {

    private val searches = ArrayList<RecentSearchModel>()

    var onItemClick: ((RecentSearchModel) -> (Unit))? = null

    fun setItems(items: List<RecentSearchModel>?) {
        searches.clear()
        items?.apply { searches.addAll(items) }
        notifyDataSetChanged()
    }

    override fun getItemCount() = searches.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchViewHolder {
        val binding = ViewRecentSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentSearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
        holder.bind(searches[position])
    }

    inner class RecentSearchViewHolder(private val binding: ViewRecentSearchBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(searches[adapterPosition])
            }
        }

        fun bind(item: RecentSearchModel) = binding.apply {
            binding.recentSearch = item
        }
    }
}
