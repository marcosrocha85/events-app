package com.marcosrocha85.events.application.presentation.welcome.pages.aboutMe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.marcosrocha85.events.application.base.BaseFragment
import com.marcosrocha85.events.application.presentation.welcome.pages.aboutMe.adapter.SkillListAdapter
import com.marcosrocha85.events.databinding.FragmentAboutMeBinding
import com.marcosrocha85.events.domain.model.Skill

class AboutMeFragment(skillList: MutableList<Skill>) : BaseFragment() {
    private lateinit var binding: FragmentAboutMeBinding
    val adapter = SkillListAdapter(skillList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutMeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupSkillList()
    }

    private fun setupSkillList() {
        binding.skills.adapter = adapter

        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.COLUMN
        layoutManager.justifyContent = JustifyContent.FLEX_END
        binding.skills.layoutManager = layoutManager
    }

}