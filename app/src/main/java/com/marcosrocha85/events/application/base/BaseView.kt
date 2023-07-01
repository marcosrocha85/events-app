package com.marcosrocha85.events.application.base

interface BaseView<VM: BaseViewModel> {
    val viewModel: VM?
}