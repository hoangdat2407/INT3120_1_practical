package org.example.ex10

fun main() {
    weatherBroadcast("Ankara", 27, 31, 82)
    weatherBroadcast("Tokyo", 32, 36, 10)
    weatherBroadcast("Cape Town", 59, 64, 2)
    weatherBroadcast("Guatemala City", 50, 55, 7)

}
fun weatherBroadcast(city: String, lTemp: Int, hTemp: Int, chanceOfRain: Int) {
    println("City: $city")
    println("Low temperature: $lTemp, High temperature: $hTemp")
    println("Chance of rain: $chanceOfRain%")
    println()
}