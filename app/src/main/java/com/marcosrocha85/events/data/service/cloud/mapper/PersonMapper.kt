package com.marcosrocha85.events.data.service.cloud.mapper

import com.marcosrocha85.events.data.base.BaseMapper
import com.marcosrocha85.events.data.service.cloud.model.PersonData
import com.marcosrocha85.events.domain.model.Person

class PersonMapper : BaseMapper<PersonData, Person>() {
    override fun dataToModel(data: PersonData): Person {
        return Person(
            data.name,
            data.email
        )
    }

    override fun modelToData(model: Person): PersonData {
        return PersonData(
            model.name,
            model.email
        )
    }
}