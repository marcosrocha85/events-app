package com.marcosrocha85.events.application.presentation.welcome

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.marcosrocha85.events.application.EventsApplication
import com.marcosrocha85.events.application.base.BaseViewModel
import com.marcosrocha85.events.application.toMain
import com.marcosrocha85.events.application.utils.ViewStatus
import com.marcosrocha85.events.domain.interactors.GetSkillsUseCase
import com.marcosrocha85.events.domain.model.Skill

interface WelcomeViewModel : BaseViewModel {
    fun loadSkills()
    fun goNextActivity()

    class Factory(context: Context) : BaseViewModel.Factory(context), WelcomeViewModel {
        private val getSkillUseCase = GetSkillsUseCase()
        val skills = MutableLiveData<List<Skill>>()

        override fun goNextActivity() {
            context.toMain()
        }

        override fun loadSkills() {
            this.viewStatus.value?.isLoading = true
            buildObserver(getSkillUseCase.execute(),
                {
                    skills.value = it
                    this.viewStatus.value?.isLoading = false
                },
                {
                    this.viewStatus.value?.error = it
                    this.viewStatus.value?.isLoading = false
                })
        }
    }
}