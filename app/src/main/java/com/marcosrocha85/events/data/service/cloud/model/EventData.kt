package com.marcosrocha85.events.data.service.cloud.model

import com.google.gson.annotations.Expose

data class EventData(
    @Expose
    val people: List<PersonData> = emptyList(),
    @Expose
    val date: Long,
    @Expose
    val description: String,
    @Expose
    val image: String,
    @Expose
    val longitude: Double,
    @Expose
    val latitude: Double,
    @Expose
    val price: Float,
    @Expose
    val title: String,
    @Expose
    val id: String
)
