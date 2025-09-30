enum class Daypart {
    MORNING, AFTERNOON, EVENING
}
data class Event(
    val title : String, // not nullable
    val description : String?=null, //nullable
    val daypart : Daypart,
    val durationInMinutes : Int
)
fun main() {
    val yourEvents = mutableListOf<Event>()
    yourEvents.add(Event(title = "Wake up", description = "Time to get up", daypart = Daypart.MORNING, durationInMinutes = 0))
    yourEvents.add(Event(title = "Eat breakfast", daypart = Daypart.MORNING, durationInMinutes = 15))
    yourEvents.add(Event(title = "Learn about Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30))
    yourEvents.add(Event(title = "Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60))
    yourEvents.add(Event(title = "Watch latest DevBytes video", daypart = Daypart.AFTERNOON, durationInMinutes = 10))
    yourEvents.add(Event(title = "Check out latest Android Jetpack library", daypart = Daypart.EVENING, durationInMinutes = 45))
	val groupedEvent = yourEvents.groupBy{it.daypart}
    val morningEvent = groupedEvent[Daypart.MORNING]?:listOf()
    val afternoonEvent = groupedEvent[Daypart.AFTERNOON]?:listOf()
    val eveningEvent = groupedEvent[Daypart.EVENING]?:listOf()
    println("${Daypart.MORNING}: ${morningEvent.size}")
    println("${Daypart.AFTERNOON}: ${afternoonEvent.size}")
    println("${Daypart.EVENING}: ${eveningEvent.size}")
}

