package com.marcosrocha85.events.domain.interactors

import com.marcosrocha85.events.application.exception.EmptyDataException
import com.marcosrocha85.events.data.service.EventRepositoryImpl
import com.marcosrocha85.events.domain.model.Event
import io.reactivex.rxjava3.core.Observable

class GetEventDetailsUseCase : BaseUseCase<Observable<Event>> {
    private val repository = EventRepositoryImpl()
    var id: String = ""

    private fun validate() {
        if (id.isEmpty()) {
            throw EmptyDataException()
        }
    }

    override fun execute(): Observable<Event> {
        return try {
            repository.getEvent(id)
        } catch (e: Exception) {
            Observable.error(e)
        }
    }
}