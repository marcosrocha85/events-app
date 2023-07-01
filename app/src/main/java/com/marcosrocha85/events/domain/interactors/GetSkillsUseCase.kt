package com.marcosrocha85.events.domain.interactors

import com.marcosrocha85.events.data.service.EventRepositoryImpl
import com.marcosrocha85.events.domain.model.Event
import com.marcosrocha85.events.domain.model.Skill
import io.reactivex.rxjava3.core.Observable

class GetSkillsUseCase : BaseUseCase<Observable<List<Skill>>> {
    private val repository = EventRepositoryImpl()

    override fun execute(): Observable<List<Skill>> {
        return try {
            repository.getSkills()
        } catch (e: Exception) {
            Observable.error(e)
        }
    }
}