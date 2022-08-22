package com.akki.filmyapp.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.akki.filmyapp.databinding.FragmentMovieListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding

    private val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var movieAdapter: MovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listMovie.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
        arguments?.let {
            it.getString(KEY_TYPE)?.let {
                fetchMovies(it)
            }
        }
    }

    private fun fetchMovies(type: String) {
        homeViewModel.fetchMovies(type)
        lifecycleScope.launchWhenStarted {
            homeViewModel.moviesData.collect {
                movieAdapter.updateItem(it?.results ?: emptyList())
            }
        }
    }

    companion object {
        private const val KEY_TYPE = "type"
        fun newInstance(type: String): MovieListFragment {
            val args = Bundle().apply {
                putString(KEY_TYPE, type)
            }
            return MovieListFragment().apply {
                arguments = args
            }
        }
    }
}