package com.akki.filmyapp.home.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.akki.filmyapp.home.presentation.details.MovieDetailActiivty
import com.akki.filmyapp.logging.ILogger
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragmentV2 : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var logger: ILogger

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                HomeScreenV2(
                    homeViewModel
                ) {
                    launchDetailActivity(it)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.fetchMovies("popular")
    }

    private fun launchDetailActivity(id: Int) {
        startActivity(
            Intent(
                activity,
                MovieDetailActiivty::class.java
            )
        )
    }
}
