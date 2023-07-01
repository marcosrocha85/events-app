package com.marcosrocha85.events.application.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcosrocha85.events.R
import com.marcosrocha85.events.application.presentation.home.adapter.EventListAdapter
import com.marcosrocha85.events.databinding.ActivityMainBinding
import com.marcosrocha85.events.domain.model.Event

class MainActivity : AppCompatActivity() {
    private val eventList: MutableList<Event> = mutableListOf()
    private var binding = ActivityMainBinding.inflate(LayoutInflater.from(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.eventList
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = EventListAdapter(eventList)
    }
}