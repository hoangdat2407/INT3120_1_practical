package org.example.song_catalog

class Main {
}
class Song {
    var title: String = ""
    var artist: String = ""
    var year_published: Int = 0
    var play_count: Int = 0
    var isPopular: Boolean = false
    fun printSongDes() {
        println("$title, performed by $artist, was released in $year_published.")
    }
}