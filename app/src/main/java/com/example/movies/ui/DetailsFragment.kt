package com.example.movies.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.movies.databinding.FragmentDetailsBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class DetailsFragment : Fragment() {


    lateinit var detailsViewModel: DetailsViewModel
    lateinit var binding: FragmentDetailsBinding

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        detailsViewModel =
            ViewModelProvider(
                this,
                mViewModelFactory
            ).get(DetailsViewModel::class.java)
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.detailsViewModel = detailsViewModel

        arguments?.let {
            val title = DetailsFragmentArgs.fromBundle(it).title
            detailsViewModel.getMovieDetails(title)
        }
        observeResult()
        return binding.root
    }

    private fun observeResult() {
        detailsViewModel.detailsLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.detailsViewModel = detailsViewModel
                Glide.with(binding.imgPoster.context)
                    .load(detailsViewModel.detailsModel.poster)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.imgPoster)
                binding.executePendingBindings()
            }

        })
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}