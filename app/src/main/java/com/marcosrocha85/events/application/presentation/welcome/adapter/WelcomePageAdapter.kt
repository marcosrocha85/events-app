package com.marcosrocha85.events.application.presentation.welcome.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.marcosrocha85.events.application.presentation.welcome.pages.LocationInfoFragment
import com.marcosrocha85.events.application.presentation.welcome.pages.WhatIsForFragment
import com.marcosrocha85.events.application.presentation.welcome.pages.aboutMe.AboutMeFragment
import com.marcosrocha85.events.domain.model.Skill

class WelcomePageAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private var aboutMePage: AboutMeFragment? = null
    private var skillList: MutableList<Skill> = mutableListOf()

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        val fragment = when (position) {
            0 -> AboutMeFragment(skillList)
            1 -> WhatIsForFragment()
            else -> LocationInfoFragment()
        }
        if (fragment is AboutMeFragment) {
            aboutMePage = fragment
        }
        return fragment
    }

    fun updateSkills(skills: List<Skill>) {
        skillList.clear()
        skillList.addAll(skills)
        aboutMePage?.adapter?.notifyItemRangeChanged(0, skills.count())
    }
}