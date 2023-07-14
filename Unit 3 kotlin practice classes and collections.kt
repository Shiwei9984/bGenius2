//Task 1 solution
data class Event(
    val title: String,
    val description: String? = null,
    val daypart: String,
    val duration: Int,
)
//Task 2 solution
enum class Daypart {
    Morning,
    Afternoon,
    Evening,
}
//Task 3 solution
val events = mutableListOf<Event>(event1, event2, event3, event4, event5, event6)
//Task 4 solution
val shortEvents = events.filter { it.durationInMinutes < 60 }
println("You have ${shortEvents.size} short events.")
//Task 5 solution
val groupedEvents = events.groupBy { it.daypart }
groupedEvents.forEach { (daypart, events) ->
    println("$daypart: ${events.size} events")
}
//Task 6 solution
println("Last event of the day: ${events.last().title}")
//Task 7 solution
val Event.durationOfEvent: String
    get() = if (this.durationInMinutes < 60) {
        "short"
    } else {
        "long"
    }