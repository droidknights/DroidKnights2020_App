package com.droidknights.app2020.ui.schedule.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.droidknights.app2020.databinding.ItemSessionDetailTagBinding

class DetailTagAdapter : RecyclerView.Adapter<TagViewHolder>() {

    var tags = emptyList<String>()

    override fun getItemCount() = tags.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        return TagViewHolder(
            ItemSessionDetailTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.bind(tags[position])
    }
}

class TagViewHolder(private val binding: ItemSessionDetailTagBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(tag: String) {
        binding.tag = tag
        binding.executePendingBindings()
    }
}
