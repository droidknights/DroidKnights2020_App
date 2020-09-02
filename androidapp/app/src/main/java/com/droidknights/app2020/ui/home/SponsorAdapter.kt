package com.droidknights.app2020.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.droidknights.app2020.data.Sponsor
import com.droidknights.app2020.databinding.ItemHomeSponsorBinding

class SponsorAdapter(
    private val sponsorList: List<Sponsor>
) : RecyclerView.Adapter<SponsorViewHolder>() {

    override fun getItemCount() = sponsorList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SponsorViewHolder {
        return SponsorViewHolder(
            ItemHomeSponsorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SponsorViewHolder, position: Int) {
        holder.bind(sponsorList[position])
    }
}

class SponsorViewHolder(
    private val binding: ItemHomeSponsorBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Sponsor) {
        binding.item = item
        binding.executePendingBindings()
    }
}
