package com.marcosrocha85.events.application.utils

data class ViewStatus(
    var isLoading: Boolean,
    var error: Throwable?
) {
    constructor(isLoading: Boolean) : this(isLoading, null)
}
