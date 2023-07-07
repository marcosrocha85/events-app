package com.marcosrocha85.events.application.presentation.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.marcosrocha85.events.application.base.BaseViewModel
import com.marcosrocha85.events.application.utils.ViewStatus
import com.marcosrocha85.events.domain.interactors.GetEventsUseCase
import com.marcosrocha85.events.domain.model.Event

interface MainViewModel : BaseViewModel {
    fun loadEvents()

    class Factory(context: Context) : BaseViewModel.Factory(context), MainViewModel {
        private val getEventsUseCase = GetEventsUseCase()
        val events = MutableLiveData<List<Event>>()

        override fun loadEvents() {
            this.viewStatus.value = ViewStatus(isLoading = true)
            buildObserver(getEventsUseCase.execute(),
                {
                    events.value = it
                    this.viewStatus.value = ViewStatus(isLoading = false)
                },
                {
                    this.viewStatus.value = ViewStatus(isLoading = false, error = it)
                })
        }
    }
}