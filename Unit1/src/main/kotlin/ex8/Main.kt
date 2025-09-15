package org.example.ex8

fun main() {
    val steps = 4_000
    val caloriesBurned = stepsToCalories(steps)
    println("Walking $steps steps burns $caloriesBurned calories")
}

fun stepsToCalories(stepCount: Int): Double =
    stepCount * 0.04
/*
fun main() {
    val Steps = 4000
    val caloriesBurned = PEDOMETERstepsTOcalories(Steps);
    println("Walking $Steps steps burns $caloriesBurned calories")
}

fun PEDOMETERstepsTOcalories(NumberOFStepS: Int): Double {
    val CaloriesBURNEDforEachStep = 0.04
    val TotalCALORIESburned = NumberOFStepS * CaloriesBURNEDforEachStep
    return TotalCALORIESburned
}
* */