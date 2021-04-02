package com.techethic.compose.a11ystickylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.text
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.techethic.compose.a11ystickylist.ui.theme.ComposeAccessibleStickyListTheme
import com.techethic.compose.a11ystickylist.ui.theme.Purple200
import com.techethic.compose.a11ystickylist.ui.theme.Purple700

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setContent {
            MovieList(grouped = viewModel.movies
                .sortedByDescending { it.rate }
                .groupBy { it.rate }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAccessibleStickyListTheme {
        MovieList(grouped = listOf(
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
        ).groupBy { it.rate })
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieList(grouped: Map<Int, List<Movie>>) {
    LazyColumn {
        grouped.forEach { (initial, moviesForRate) ->
            stickyHeader {
                RateHeader(initial)
            }

            items(moviesForRate) { contact ->
                MovieItem(contact)
            }
        }
    }
}

@Composable
fun RateHeader(rate : Int){
    Row (modifier = Modifier
        .background(Color.Blue)
        .semantics(mergeDescendants = true){}
        .padding(20.dp)){
       /* Text(
            modifier = Modifier
                .padding(20.dp)
                .background(Color.Red).semantics(mergeDescendants = true) {}
            color = Purple700,
            text = rate.toString())*/
        Text( modifier = Modifier.weight(1f).fillMaxHeight().background(Color.Red),
            text = rate.toString())
    }
}

@Composable
fun MovieItem(movie : Movie){
    Row(modifier = Modifier
        .background(Color.Green)
        .semantics(mergeDescendants = true){}
        .padding(20.dp)
        ) {
            Text( modifier = Modifier.weight(1f).fillMaxHeight(),
                  text = movie.title)
            Text( text = movie.rate.toString())
    }
}