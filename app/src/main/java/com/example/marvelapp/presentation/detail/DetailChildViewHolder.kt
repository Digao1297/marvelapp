package com.example.marvelapp.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.marvelapp.databinding.ItemChildDetailBinding
import com.example.marvelapp.framework.imageloader.ImageLoader

class DetailChildViewHolder(
    itemBinding: ItemChildDetailBinding,
    private val imageLoader: ImageLoader,
) : ViewHolder(itemBinding.root) {

    private val imageCategory: ImageView = itemBinding.imageItemCategory

    fun bind(detailChildVE: DetailChildVE) {
        imageLoader.load(imageCategory, detailChildVE.imageUrl)
    }

    companion object {
        fun create(parent: ViewGroup, imageLoader: ImageLoader): DetailChildViewHolder {
            val itemBiding =
                ItemChildDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return DetailChildViewHolder(itemBiding, imageLoader)
        }
    }
}