package com.marcosrocha85.events.application.presentation.welcome.pages.aboutMe.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marcosrocha85.events.R
import com.marcosrocha85.events.domain.model.Skill

class SkillListAdapter(private val skillList: List<Skill>) :
    RecyclerView.Adapter<SkillListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pill, parent, false) as View
        return MyViewHolder(textView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.eventTitle.text = skillList[position].name
    }

    override fun getItemCount() = skillList.size

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val eventTitle: TextView = view.findViewById(R.id.pill_title)
    }
}