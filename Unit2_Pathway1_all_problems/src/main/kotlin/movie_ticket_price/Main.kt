package org.example.movie_ticket_price

class Main {
}
fun main() {
    val child = 5
    val adult = 28
    val senior = 87

    val isMonday = true

    println("The movie ticket price for a person aged $child is \$${ticketPrice(child, isMonday)}.")
    println("The movie ticket price for a person aged $adult is \$${ticketPrice(adult, isMonday)}.")
    println("The movie ticket price for a person aged $senior is \$${ticketPrice(senior, isMonday)}.")
}

fun ticketPrice(age: Int, isMonday: Boolean): Int {
    // Fill in the code.
    if (0<= age && age <= 12) {
        return 15
    }
    if (age <= 60) {
        if (isMonday) {
            return 25
        }
        return 30
    }
    if (age <= 100) {
        return  20
    }
    return -1;
}