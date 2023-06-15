package com.example.marvelapp.presentation.detail

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.marvelapp.databinding.FragmentDetailBinding
import com.example.marvelapp.framework.imageloader.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {


    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = _binding!!

    private val args by navArgs<DetailFragmentArgs>()

    private val viewModel: DetailViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailViewArg = args.detailViewArg
        binding.imageCharacter.run {
            transitionName = detailViewArg.name

            imageLoader.load(this, detailViewArg.imageUrl)

        }
        setSharedElementTransactionOnEnter()

        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                DetailViewModel.UiState.Loading -> {}
                is DetailViewModel.UiState.Success ->
                    binding.recyclerParenteDetail.run {
                        setHasFixedSize(true)
                        adapter = DetailParentAdapter(uiState.detailParentList, imageLoader)
                    }
                DetailViewModel.UiState.Error -> {}
            }

        }

        viewModel.getComics(detailViewArg.characterId)
    }

    private fun setSharedElementTransactionOnEnter() {
        TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
            .apply {
                sharedElementEnterTransition = this
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}