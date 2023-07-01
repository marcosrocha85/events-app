package com.marcosrocha85.events.domain.interactors

interface BaseUseCase<T> {
    fun execute(): T
}