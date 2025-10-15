package org.example.temperature_converter

class Main {
}
fun main() {
    // Fill in the code.
    val C2F: (Double) -> Double = {
        c -> (9.0/5.0) * c + 32
    }
    val K2C: (Double) -> Double = {
            k -> k - 273.15
    }
    val F2K: (Double) -> Double = {
            f -> (5.0/9.0) * (f - 32) + 273.15
    }
    printFinalTemperature(27.0, "Celcius", "Fahrenheit", C2F)
    printFinalTemperature(350.0, "Kelvin","Celcius", K2C)
    printFinalTemperature(10.0, "Fahrenheit","Kelvin",F2K)
}


fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}