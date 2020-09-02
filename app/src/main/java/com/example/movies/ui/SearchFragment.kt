package com.example.movies.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.R
import com.example.movies.data.SearchResultAdapter
import com.example.movies.databinding.FragmentSearchBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {


    private lateinit var viewModel: SearchViewModel
    private val searchResultAdapter = SearchResultAdapter()
    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel =
            ViewModelProvider(requireActivity(), mViewModelFactory).get(SearchViewModel::class.java)
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        setAdapter()
        observeSearchResult()
        return binding.root
    }

    private fun observeSearchResult() {
        viewModel.searchResultLiveData.observe(viewLifecycleOwner, Observer {
            searchResultAdapter.searchList.clear()
            if (it != null) {
                searchResultAdapter.searchList.addAll(it)
            }
            searchResultAdapter.notifyDataSetChanged()
        })
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun setAdapter() {
        searchResultAdapter.onItemClick = {
            if (it != null) {
                val bundle = DetailsFragmentArgs(title = it).toBundle()
                findNavController().navigate(R.id.detailsFragment, bundle)
            }

        }
        binding.rvMovies.adapter = searchResultAdapter
        binding.rvMovies.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvMovies.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )

    }


}