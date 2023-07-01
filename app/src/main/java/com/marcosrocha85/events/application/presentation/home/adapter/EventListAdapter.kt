package com.marcosrocha85.events.application.presentation.home.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marcosrocha85.events.R
import com.marcosrocha85.events.application.EventsApplication
import com.marcosrocha85.events.application.utils.calculateDistance
import com.marcosrocha85.events.domain.model.Event

class EventListAdapter(private val eventList: List<Event>) :
    RecyclerView.Adapter<EventListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false) as View
        return MyViewHolder(textView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.eventImage.setImageURI(Uri.parse(eventList[position].image))
        holder.eventTitle.text = eventList[position].title
        holder.eventDescription.text = eventList[position].description
        holder.eventDistance.text = EventsApplication.appContext.getString(
            R.string.item_distance,
            calculateDistance(
                .0,
                .0,
                eventList[position].latitude,
                eventList[position].longitude)
        )
    }

    override fun getItemCount() = eventList.size

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val eventImage: ImageView = view.findViewById(R.id.event_image)
        val eventTitle: TextView = view.findViewById(R.id.event_title)
        val eventDescription: TextView = view.findViewById(R.id.event_description)
        val eventDistance: TextView = view.findViewById(R.id.event_distance)
        val eventPeople: TextView = view.findViewById(R.id.event_people)
    }
}