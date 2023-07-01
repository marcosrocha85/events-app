package com.marcosrocha85.events.domain.repository

import com.marcosrocha85.events.domain.model.Event
import com.marcosrocha85.events.domain.model.Skill
import io.reactivex.rxjava3.core.Observable

interface EventRepository {
    fun getEvents(): Observable<List<Event>>
    fun getEvent(id: String): Observable<Event>
    fun checkInEvent(id: String, name: String, email: String): Observable<Any>
    fun getSkills(): Observable<List<Skill>>
}