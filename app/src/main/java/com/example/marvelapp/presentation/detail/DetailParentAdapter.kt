package com.example.marvelapp.presentation.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.marvelapp.framework.imageloader.ImageLoader

class DetailParentAdapter(
    private val detailParentList: List<DetailParentVE>,
    private val imageLoader: ImageLoader
) : Adapter<DetailParentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailParentViewHolder =
        DetailParentViewHolder.create(parent, imageLoader)

    override fun onBindViewHolder(holder: DetailParentViewHolder, position: Int) {
        holder.bind(detailParentList[position])
    }

    override fun getItemCount(): Int = detailParentList.size
}