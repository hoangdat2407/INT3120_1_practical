package org.example.ex3

fun main() {
    var discountPercentage: Int = 0
    var offer: String = ""
    val item = "Google Chromecast"
    discountPercentage = 20
    offer = "Sale - Up to $discountPercentage% discount on $item! Hurry up!"

    println(offer)
}
/*
* the cause of the error is val variable can not be changed
* the output when i correct code should be "Sale - Up to 20% discount on Google Chromecast! Hurry up!"
* */