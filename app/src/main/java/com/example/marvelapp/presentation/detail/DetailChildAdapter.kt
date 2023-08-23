package com.example.marvelapp.presentation.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.marvelapp.framework.imageloader.ImageLoader

class DetailChildAdapter(
    private val detailChildList: List<DetailChildVE>,
    private val imageLoader: ImageLoader,
) : Adapter<DetailChildViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailChildViewHolder =
        DetailChildViewHolder.create(parent, imageLoader)

    override fun onBindViewHolder(holder: DetailChildViewHolder, position: Int) {
        holder.bind(detailChildList[position])
    }

    override fun getItemCount(): Int = detailChildList.size
}