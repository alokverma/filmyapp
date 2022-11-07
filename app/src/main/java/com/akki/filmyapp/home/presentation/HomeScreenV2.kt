package com.akki.filmyapp.home.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.akki.filmyapp.R
import com.akki.filmyapp.api.Constants
import com.akki.filmyapp.navigation.Screen
import com.akki.filmyapp.ui.theme.Typography

@Composable
fun HomeScreenV2(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val homeState = remember { viewModel.homeUiState }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            homeState.value.movieResult.let {
                items(it) { movie ->
                    MovieListItem(
                        contentDescription = "",
                        title = movie.title,
                        imageUrl = Constants.IMAGE_BASE_URL + movie.posterPath,
                        movieGenre = movie.overview,
                        movieId = movie.id,
                        navController
                    )
                }
            }
        }
        if (homeState.value.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center),
                color = Color.Magenta
            )
        }
        if (homeState.value.error.isNotBlank()) {
            Text(
                modifier = Modifier.fillMaxSize(),
                text = homeState.value.error,
                style = Typography.body1
            )
        }
    }
}

@Composable
fun MovieListItem(
    contentDescription: String,
    title: String,
    imageUrl: String,
    movieGenre: String,
    movieId: Int,
    navController: NavHostController
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
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(
                            route = Screen.DetailScreen.passMovieNameImageId(
                                movieId = movieId,
                                movieImage = "imageUrl",
                                movieName = title,
                                description = movieGenre
                            )
                        )
                    }
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
                        color = MaterialTheme.colors.onSecondary,
                        fontWeight = FontWeight.W900,
                        fontSize = 16.sp,
                        style = Typography.h5
                    )
                    Text(
                        modifier = Modifier
                            .padding(
                                vertical = 4.dp,
                                horizontal = 8.dp
                            )
                            .fillMaxWidth(.8f),
                        text = movieGenre,
                        color = MaterialTheme.colors.onSecondary,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 16.sp,
                        style = Typography.body1
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
            contentDescription = contentDescription,
            modifier = Modifier.clip(CutCornerShape(1)),
            imageUrl = imageUrl
        )
    }
}

@Composable
fun AsyncImage(
    contentDescription: String,
    modifier: Modifier,
    imageUrl: String,
    resourceId: Int = R.drawable.leo
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        placeholder = painterResource(resourceId),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        error = painterResource(resourceId),
        modifier = modifier
    )
}
