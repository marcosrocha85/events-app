package com.marcosrocha85.events.domain.model

data class Event(
    val people: List<Person> = emptyList(),
    val date: Long,
    val description: String,
    val image: String,
    val longitude: Double,
    val latitude: Double,
    val price: Float,
    val title: String,
    val id: String
)
