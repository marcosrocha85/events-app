package com.marcosrocha85.events.data.service.cloud.model

import com.google.gson.annotations.Expose

data class EventCheckInData(
    @Expose
    val eventId: String,
    val name: String,
    val email: String,
)
