enum class Daypart {
    MORNING, AFTERNOON, EVENING
}
data class Event(
    val title : String, // not nullable
    val description : String?=null, //nullable
    val dayPart : Daypart,
    val durationInMinutes : Int
)
fun main() {
    val event = Event(
        title = "Study Kotlin",
        description = "Commit to studying Kotlin at least 15 minutes per day.",
        dayPart = Daypart.MORNING,
        durationInMinutes = 15
    )

    println(event)
}
