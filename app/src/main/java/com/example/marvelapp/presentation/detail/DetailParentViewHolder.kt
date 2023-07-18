package com.example.marvelapp.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.marvelapp.databinding.ItemParentDetailBinding
import com.example.marvelapp.framework.imageloader.ImageLoader

class DetailParentViewHolder(
    itemBinding: ItemParentDetailBinding,
    private val imageLoader: ImageLoader,
) : ViewHolder(itemBinding.root) {

    private val textItemCategory = itemBinding.textItemCategory
    private val recyclerChildDetail = itemBinding.recyclerChildDetail

    fun bind(detailParentVE: DetailParentVE) {
        textItemCategory.text = itemView.context.getString(detailParentVE.categoryStringResId)

        recyclerChildDetail.run {
            setHasFixedSize(true)
            adapter = DetailChildAdapter(detailParentVE.detailChildList, imageLoader)
        }
    }

    companion object {
        fun create(parent: ViewGroup, imageLoader: ImageLoader): DetailParentViewHolder {
            val itemParentDetailBinding =
                ItemParentDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return DetailParentViewHolder(itemParentDetailBinding, imageLoader)
        }
    }
}