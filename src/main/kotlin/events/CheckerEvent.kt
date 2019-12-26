package events

import javafx.event.Event
import javafx.event.EventType

open class CheckerEvent(eventType: EventType<*>) : Event(eventType) {
    companion object {
        val ON_GONE = EventType<CheckerEvent>("ON_CHECKER_GONE")
    }
}

class CheckerClickEvent(eventType: EventType<*>, val x: Double, val y: Double) : Event(eventType) {
    companion object {
        val ON_CLICK = EventType<CheckerClickEvent>("ON_CHECKER_CLICK")
    }
}