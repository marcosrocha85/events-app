package com.marcosrocha85.events.domain.interactors

import com.marcosrocha85.events.data.service.EventRepositoryImpl
import com.marcosrocha85.events.domain.model.Event
import io.reactivex.rxjava3.core.Observable

class GetEventsUseCase : BaseUseCase<Observable<List<Event>>> {
    private val repository = EventRepositoryImpl()

    override fun execute(): Observable<List<Event>> {
        return try {
            repository.getEvents()
        } catch (e: Exception) {
            Observable.error(e)
        }
    }
}