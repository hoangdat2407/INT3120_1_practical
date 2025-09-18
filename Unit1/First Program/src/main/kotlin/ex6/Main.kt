package org.example.ex6

fun main() {
    val firstNumber = 10
    val secondNumber = 5
    val thirdNumber = 8

    val result = add(firstNumber, secondNumber)
    val anotherResult = add(firstNumber, thirdNumber)

    println("$firstNumber + $secondNumber = $result")
    println("$firstNumber + $thirdNumber = $anotherResult")
    println("$firstNumber - $secondNumber = ${add(firstNumber, secondNumber)}")
    println("$firstNumber - $thirdNumber = ${subtract(firstNumber, thirdNumber)}")
}

// Define add() function below this line
fun add(firstNum: Int, secondNum: Int) = firstNum + secondNum
fun subtract(firstNum: Int, secondNum: Int) = firstNum - secondNum