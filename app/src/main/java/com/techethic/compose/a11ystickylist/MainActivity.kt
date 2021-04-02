package com.techethic.compose.a11ystickylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.techethic.compose.a11ystickylist.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setContent {

            ComposeAccessibleStickyListTheme {
                /*MovieList(viewModel.movies)*/
                MovieStickyList(grouped = viewModel.movies.groupBy { it.rate })

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val movies = listOf(
        Movie("Titanic", 3),
        Movie("Lost in translation", 1),
        Movie("Home alone", 4),
        Movie("Pulp Fiction", 5),
        Movie("Fight club", 5),
        Movie("Forrest gump", 5),
        Movie("Inception", 3),
        Movie("Requiem for a dream", 4),
        Movie("Dirty Dancing", 3),
        Movie("Batman", 1),
        Movie("Cars", 4),
        Movie("Jurassic Park", 5),
        Movie("E.T.", 5),
        Movie("Harry Potter", 5),
        Movie("The Pianist", 3),
        Movie("Sherlock Holmes", 4)
    ).sortedByDescending { it.rate }
    /*ComposeAccessibleStickyListTheme {
        MovieStickyList(grouped = movies.groupBy { it.rate })
    }*/
    ComposeAccessibleStickyListTheme {
        MovieList(movies)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieStickyList(grouped: Map<Int, List<Movie>>) {
    val backgroundColors = listOf(BlueJordy, OrangeSandy, PinkMulberry, BlueTurquoise)
    LazyColumn {
        grouped.forEach { (initial, moviesForRate) ->
            stickyHeader {
                RateHeader(initial)
            }

            items(moviesForRate) { contact ->
                MovieItem(contact, backgroundColors.random())
            }
        }
    }
}

@Composable
fun MovieList(movies: List<Movie>) {
    val backgroundColors = listOf(BlueJordy, OrangeSandy, PinkMulberry, BlueTurquoise)
    LazyColumn {
        items(movies) { message ->
            MovieItem(message, backgroundColors.random())
        }
    }
}

@Composable
fun RateHeader(rate: Int) {
    Row(
        modifier = Modifier
            .height(50.dp)
            .background(Color.White)
            .semantics(mergeDescendants = true) {},
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(start = 16.dp),
            //IMPROVING CONTENT DESCRIPTION NOT WORKING
            /*.semantics {
                contentDescription = stringResource(R.string.rate_header_content_description,rate.toString())
            },*/
            text = rate.toString(),
            style = MaterialTheme.typography.h1
        )
    }
}

@Composable
fun MovieItem(movie: Movie, backgroundColor: Color) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(16.dp)
    ) {
        Row(modifier = Modifier
            .semantics(mergeDescendants = true) {}
            .height(80.dp)
            .background(backgroundColor),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                text = movie.title,
                color = Color.White,
                style = MaterialTheme.typography.body1
            )
            Text(
                text = movie.rate.toString(),
                color = Color.White,
                modifier = Modifier.padding(end = 8.dp),
                style = MaterialTheme.typography.body1
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_star_rate), contentDescription = null,
                modifier = Modifier.padding(end = 16.dp),
            )
        }
    }

}