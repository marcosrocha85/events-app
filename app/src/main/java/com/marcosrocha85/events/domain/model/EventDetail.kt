package com.marcosrocha85.events.domain.model

data class EventDetail(
    val people: List<Person> = emptyList(),
    val date: Long,
    val description: String,
    val image: String,
    val longitude: Float,
    val latitude: Float,
    val price: Float,
    val title: String,
    val id: String
)
