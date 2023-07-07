package com.marcosrocha85.events.application.presentation.home

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcosrocha85.events.application.base.BaseActivity
import com.marcosrocha85.events.application.presentation.home.adapter.EventListAdapter
import com.marcosrocha85.events.databinding.ActivityMainBinding
import com.marcosrocha85.events.domain.model.Event

class MainActivity : BaseActivity<MainViewModel>() {
    private val eventList: MutableList<Event> = mutableListOf()
    private lateinit var binding: ActivityMainBinding
    override val viewModel = MainViewModel.Factory(this)

    override fun initialize() {
        setupRecyclerView()
        setupViewBinding()
        viewModel.loadEvents()
    }

    override fun getInflatedLayout(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.eventList
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = EventListAdapter(eventList)
    }

    private fun setupViewBinding() {
        viewModel.viewStatus.observe(this) {
            it.error?.let { err ->
                Toast.makeText(this, err.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
        viewModel.events.observe(this) {
            eventList.clear()
            eventList.addAll(it)
            binding.eventList.adapter?.notifyItemRangeChanged(0, eventList.count())
        }
    }
}