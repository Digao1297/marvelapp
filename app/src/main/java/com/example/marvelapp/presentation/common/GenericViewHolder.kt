package com.example.marvelapp.presentation.common

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding

abstract class GenericViewHolder<T>(
    itemBinding: ViewBinding,
) : ViewHolder(
    itemBinding.root,
) {

    abstract fun bind(data: T)
}