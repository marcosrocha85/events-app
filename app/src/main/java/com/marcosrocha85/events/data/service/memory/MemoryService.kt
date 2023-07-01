package com.marcosrocha85.events.data.service.memory

import com.marcosrocha85.events.data.service.memory.model.SkillData
import io.reactivex.rxjava3.core.Observable

interface MemoryService {
    fun getSkills(): Observable<List<SkillData>>

    class Factory : MemoryService {
        override fun getSkills(): Observable<List<SkillData>> {
            val list = mutableListOf<SkillData>(
                SkillData("Android Java/Kotlin", 3),
                SkillData("Delphi", 11),
                SkillData("Firebird", 8),
                SkillData("iOS Swift", 4),
                SkillData("Javascript", 6),
                SkillData("MySql", 7),
                SkillData("Nest.js", 1),
                SkillData("Node.js", 2),
                SkillData("PHP", 0),
                SkillData("Postgres", 9),
                SkillData("Python", 10),
                SkillData("Typescript", 5)
            )

            return Observable.just(list)
        }
    }
}