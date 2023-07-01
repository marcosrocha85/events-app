package com.marcosrocha85.events.data.service.cloud.model

import com.google.gson.annotations.Expose

data class PersonData(
    @Expose
    val name: String,
    @Expose
    val email: String
)
