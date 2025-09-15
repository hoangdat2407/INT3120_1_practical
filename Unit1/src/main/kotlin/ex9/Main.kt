package org.example.ex9

fun main() {
    println(compareTime(300,250))
    println(compareTime(300,300))
    println(compareTime(200,220))
}

//fun compareTime(timeSpentToday: Int, timeSpentYesterday: Int): Boolean {
//    return timeSpentToday > timeSpentYesterday
//}
fun compareTime(timeSpentToday: Int, timeSpentYesterday: Int) = timeSpentToday > timeSpentYesterday