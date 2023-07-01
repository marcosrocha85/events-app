package com.marcosrocha85.events.data.service.cloud.mapper

import com.marcosrocha85.events.data.base.BaseMapper
import com.marcosrocha85.events.data.service.cloud.model.EventData
import com.marcosrocha85.events.domain.model.Event

class EventDetailMapper : BaseMapper<EventData, Event>() {
    private val personMapper = PersonMapper()

    override fun dataToModel(data: EventData): Event {
        return Event(
            personMapper.dataListToModelList(data.people),
            data.date,
            data.description,
            data.image,
            data.longitude,
            data.latitude,
            data.price,
            data.title,
            data.id
        )
    }

    override fun modelToData(model: Event): EventData {
        return EventData(
            personMapper.modelListToDataList(model.people),
            model.date,
            model.description,
            model.image,
            model.longitude,
            model.latitude,
            model.price,
            model.title,
            model.id
        )
    }
}