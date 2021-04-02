package com.techethic.compose.a11ystickylist

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
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
    )

}

data class Movie(val title : String, val rate : Int)