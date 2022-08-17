package com.akki.filmyapp.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.akki.filmyapp.databinding.FragmentHomeBinding
import com.akki.filmyapp.logging.ILogger
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var logger: ILogger

    @Inject
    lateinit var mAdapter: PagerImageAdapter

    private val homeViewModel: HomeViewModel by viewModels()

    private val TAG = "HomeFragment"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeChanges()
        homeViewModel.fetchTrendingVideos()
    }


    private fun observeChanges() {
        homeViewModel.trendingMovies.observe(viewLifecycleOwner) {
            logger.logMessage(TAG, it.toString())
            binding.viewPagerMain.adapter = mAdapter
            mAdapter.updateItems(it?.results ?: emptyList())
        }
    }
}