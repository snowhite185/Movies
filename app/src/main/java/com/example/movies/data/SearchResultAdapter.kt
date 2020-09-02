package com.example.movies.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.SearchItemLayoutBinding

/**
 * Created by Anusha K on 31-08-2020.
 */
class SearchResultAdapter : RecyclerView.Adapter<SearchResultAdapter.SearchViewHolder>() {

    val searchList = ArrayList<SearchItemModel>()
    var onItemClick: ((title: String?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = SearchItemLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchList[position])
        holder.binding.root.setOnClickListener() {
            onItemClick?.invoke(searchList[position].title)
        }
    }

    class SearchViewHolder(var binding: SearchItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemModel: SearchItemModel) {
            binding.itemModel = itemModel
        }
    }
}