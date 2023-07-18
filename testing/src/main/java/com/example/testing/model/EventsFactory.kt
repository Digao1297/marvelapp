package com.example.testing.model

import com.example.core.domain.model.Event

class EventsFactory {

    fun create(comic: FakeEvent): Event =
        when (comic) {
            FakeEvent.FakeEvent1 -> Event(
                id = 2211506,
                imageUrl = "http://fakeeventurl.jpg"
            )
        }

    sealed class FakeEvent {
        object FakeEvent1 : FakeEvent()
    }
}