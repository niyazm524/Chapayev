package events

import javafx.event.Event
import javafx.event.EventType

class CheckerEvent(eventType: EventType<*>) : Event(eventType) {
    companion object {
        val ON_GONE = EventType<CheckerEvent>("ON_CHECKER_GONE")
    }
}