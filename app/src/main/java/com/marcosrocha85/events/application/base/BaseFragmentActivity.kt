package com.marcosrocha85.events.application.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity

abstract class BaseFragmentActivity<VM : BaseViewModel> : FragmentActivity(), BaseView<VM> {
    abstract override val viewModel: VM?
    abstract fun initialize()
    protected abstract fun getInflatedLayout(): View

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setContentView(getInflatedLayout())

        initialize()
    }
}