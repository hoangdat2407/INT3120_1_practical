data class Event(
    val title : String, // not nullable
    val description : String?=null, //nullable
    val dayPart : String,
    val durationInMinutes : Int
)
fun main() {
    val event = Event(
        title = "Study Kotlin",
        description = "Commit to studying Kotlin at least 15 minutes per day.",
        dayPart = "Evening",
        durationInMinutes = 15
    )

    println(event)
}
