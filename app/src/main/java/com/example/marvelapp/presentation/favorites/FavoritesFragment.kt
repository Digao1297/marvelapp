package com.example.marvelapp.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.marvelapp.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _biding: FragmentFavoritesBinding? = null
    private val binding: FragmentFavoritesBinding get() = _biding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentFavoritesBinding.inflate(
        inflater,
        container,
        false,
    ).apply {
        _biding = this
    }.root

    override fun onDestroy() {
        _biding = null
        super.onDestroy()
    }
}