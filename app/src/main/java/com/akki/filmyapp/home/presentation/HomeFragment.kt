package com.akki.filmyapp.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.akki.filmyapp.databinding.FragmentHomeBinding
import com.akki.filmyapp.home.domain.model.MovieItem
import com.akki.filmyapp.logging.ILogger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var logger: ILogger

    @Inject
    lateinit var mAdapter: PagerImageAdapter

    //fixme inject via hilt
    private var mMovieAdapter: MoviePagerAdapter? = null

    private val homeViewModel: HomeViewModel by viewModels()

    private val TAG = "HomeFragment"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchHomeData()
    }

    private fun fetchHomeData() {
        homeViewModel.fetchMovies("popular")
        lifecycleScope.launchWhenStarted {
            homeViewModel.homeStateUIModel.collect {
                binding.viewPagerMain.adapter = mAdapter
                mAdapter.updateItems(it?.movieList?.results ?: emptyList())
                autoRotatePager(it?.movieList?.results)
                mMovieAdapter = MoviePagerAdapter(parentFragmentManager).apply {
                    updateItem(it?.tabs ?: emptyList())
                }
                binding.tabLayout.setupWithViewPager(binding.pager)
                binding.pager.adapter = mMovieAdapter

            }
        }
    }

    private suspend fun autoRotatePager(list: List<MovieItem>?) {
        var i = 0
        list?.forEach {
            delay(3000)
            binding.viewPagerMain.setCurrentItem(i, true)
            i++
            if (i == list.size) {
                i = 0
                autoRotatePager(list)
            }
        }
    }
}