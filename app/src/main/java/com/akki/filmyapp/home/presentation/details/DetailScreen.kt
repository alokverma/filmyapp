package com.akki.filmyapp.home.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.akki.filmyapp.home.presentation.AsyncImage
import com.akki.filmyapp.ui.theme.Typography

@Composable
fun DetailScreen(
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxHeight(.5f),
            contentDescription = "dummy",
            imageUrl = "https://www.google.com"
        )

        Text(
            modifier = Modifier.padding(4.dp),
            text = "Once Upon a Time in Hollywood 2019",
            style = Typography.h5
        )

        Text(
            modifier = Modifier.padding(4.dp),
            text = "Actors",
            style = Typography.h5
        )
        StarList()
        Text(
            modifier = Modifier.padding(4.dp),
            text = "Once Upon a Time in Hollywood is movie set in 1980 starring Leo" +
                "and Brad pitt. Its a sarcastic comedy about a actor who is loosing touch " +
                "in hollywood",
            style = Typography.body1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenComposable() {
    val navHostController = rememberNavController()
    DetailScreen(navHostController = navHostController)
}

@Composable
fun StarList(
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
    ) {
        items(100) {
            SingleStar(
                modifier = modifier,
                contentDescription = "",
                imageUrl = ""
            )
        }
    }
}

@Composable
fun SingleStar(
    modifier: Modifier = Modifier,
    contentDescription: String,
    imageUrl: String
) {
    AsyncImage(
        contentDescription = contentDescription,
        modifier = modifier
            .height(100.dp)
            .width(100.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(100.dp)),
        imageUrl = imageUrl,
        resourceId = com.akki.filmyapp.R.drawable.lionardo
    )
}
