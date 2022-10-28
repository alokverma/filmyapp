package com.akki.filmyapp.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.FloatRange
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.akki.filmyapp.api.Constants
import com.akki.filmyapp.logging.ILogger
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.random.Random

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
                MovieList(homeViewModel)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.fetchMovies("popular")
    }
}

@Composable
fun MovieListItem(
    contentDescription: String,
    title: String,
    imageUrl: String,
    movieGenre: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(
                vertical = 12.dp,
                horizontal = 18.dp
            ),
        elevation = 8.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color(
                        red = Random.nextFloat(),
                        blue = Random.nextFloat(),
                        green = Random.nextFloat()
                    )
                )

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ImageCard(
                    contentDescription = contentDescription,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 18.dp,
                            vertical = 12.dp
                        )
                        .weight(1f),
                    imageUrl = imageUrl
                )

                Column(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxHeight()
                ) {
                    Text(
                        modifier = Modifier
                            .padding(
                                vertical = 12.dp,
                                horizontal = 8.dp
                            ),
                        text = title,
                        color = MaterialTheme.colors.onPrimary,
                        fontWeight = FontWeight.W900,
                        fontSize = 16.sp
                    )
                    Text(
                        modifier = Modifier
                            .padding(
                                vertical = 4.dp,
                                horizontal = 8.dp
                            )
                            .fillMaxWidth(.8f),
                        text = movieGenre,
                        color = MaterialTheme.colors.onPrimary,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 16.sp
                    )
                }

            }
        }
    }


}

@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    contentDescription: String,
    imageUrl: String
) {
    Card(
        modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = 5.dp
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(com.akki.filmyapp.R.drawable.leo),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(CutCornerShape(1))
        )

    }
}

@Composable
fun MovieList(viewModel: HomeViewModel) {
    val movieListState = viewModel.moviesData.collectAsState()
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
    ) {
        movieListState.value?.results?.let {
            items(it) { movie ->
                MovieListItem(
                    contentDescription = "",
                    title = movie.title,
                    imageUrl = Constants.IMAGE_BASE_URL + movie.posterPath,
                    movieGenre = movie.overview
                )
            }
        }
    }
}