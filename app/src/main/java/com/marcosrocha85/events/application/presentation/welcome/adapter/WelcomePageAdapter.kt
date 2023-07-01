package com.marcosrocha85.events.application.presentation.welcome.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.marcosrocha85.events.application.presentation.welcome.pages.LocationInfoFragment
import com.marcosrocha85.events.application.presentation.welcome.pages.WhatIsForFragment
import com.marcosrocha85.events.application.presentation.welcome.pages.aboutMe.AboutMeFragment
import com.marcosrocha85.events.domain.model.Skill

class WelcomePageAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private var skillList: MutableList<Skill> = mutableListOf()
    private val aboutMePage = AboutMeFragment(skillList)

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> aboutMePage
            1 -> WhatIsForFragment()
            else -> LocationInfoFragment()
        }
    }

    fun updateSkills(skills: List<Skill>) {
        skillList.clear()
        skillList.addAll(skills)
        if (aboutMePage.isVisible) {
            aboutMePage.adapter.notifyItemRangeChanged(0, skills.count())
        }
    }
}