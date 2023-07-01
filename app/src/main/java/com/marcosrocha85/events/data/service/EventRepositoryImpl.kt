package com.marcosrocha85.events.data.service

import com.marcosrocha85.events.data.service.cloud.mapper.EventDetailMapper
import com.marcosrocha85.events.data.service.cloud.mapper.EventMapper
import com.marcosrocha85.events.data.service.cloud.model.EventCheckInData
import com.marcosrocha85.events.data.service.cloud.EventService
import com.marcosrocha85.events.data.service.memory.MemoryService
import com.marcosrocha85.events.data.service.memory.mapper.SkillMapper
import com.marcosrocha85.events.domain.model.Event
import com.marcosrocha85.events.domain.model.Skill
import com.marcosrocha85.events.domain.repository.EventRepository
import io.reactivex.rxjava3.core.Observable

class EventRepositoryImpl : EventRepository {
    companion object {
        @Volatile
        private lateinit var repository: EventRepository

        fun sharedInstance(): EventRepository {
            synchronized(this) {
                if (Companion::repository.isInitialized) return repository
                repository = EventRepositoryImpl()
                return repository
            }
        }
    }

    private val memory = MemoryService.Factory()
    private val cloud = EventService.makeRequest()
    private val skillMapper = SkillMapper()
    private val eventMapper = EventMapper()
    private val eventDetailMapper = EventDetailMapper()

    override fun getEvents(): Observable<List<Event>> {
        return cloud.getEvents().flatMap {
            return@flatMap Observable.just(eventMapper.dataListToModelList(it))
        }
    }

    override fun getEvent(id: String): Observable<Event> {
        return cloud.getEvent(id).flatMap {
            return@flatMap Observable.just(eventDetailMapper.dataToModel(it))
        }
    }

    override fun checkInEvent(id: String, name: String, email: String): Observable<Any> {
        return cloud.checkIn(EventCheckInData(id, name, email))
    }

    override fun getSkills(): Observable<List<Skill>> {
        return memory.getSkills().flatMap {
            return@flatMap Observable.just(skillMapper.dataListToModelList(it))
        }
    }
}