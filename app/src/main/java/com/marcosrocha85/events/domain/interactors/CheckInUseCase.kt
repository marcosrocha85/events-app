package com.marcosrocha85.events.domain.interactors

import com.marcosrocha85.events.application.exception.EmptyDataException
import com.marcosrocha85.events.data.service.EventRepositoryImpl
import com.marcosrocha85.events.domain.repository.EventRepository
import io.reactivex.rxjava3.core.Observable

class CheckInUseCase : BaseUseCase<Observable<Any>> {
    private val repository: EventRepository = EventRepositoryImpl()
    var id: String = ""
    var name: String = ""
    var email: String = ""

    private fun validate() {
        if (id.isEmpty() || name.isEmpty() || email.isEmpty()) {
            throw EmptyDataException()
        }
    }

    override fun execute(): Observable<Any> {
        return try {
            repository.checkInEvent(id, name, email)
        } catch (e: Exception) {
            Observable.error(e)
        }
    }
}