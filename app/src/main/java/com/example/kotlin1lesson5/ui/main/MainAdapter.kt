package com.example.kotlin1lesson5.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin1lesson5.R
import com.example.kotlin1lesson5.databinding.ItemYtBinding
import com.example.kotlin1lesson5.extentions.load
import com.example.kotlin1lesson5.models.Items

class MainAdapter(private val list: MutableList<Items>, private val onItemClick:(id: String)->Unit) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemYtBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(private var binding: ItemYtBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(items: Items) {
            binding.ivPlaylist.load(items.snippet.thumbnails.default.url)
            binding.tvTitle.text = items.snippet.title
            binding.tvItemCount.text =
                items.contentDetails.itemCount.toString() + itemView.context.getString(
                    R.string.videoSeries
                )
            itemView.setOnClickListener{
                onItemClick(items.id)
            }
        }
    }
}