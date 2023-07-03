package com.marcosrocha85.events.application.presentation.splash

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import com.marcosrocha85.events.application.EventsApplication
import com.marcosrocha85.events.application.base.BaseViewModel
import com.marcosrocha85.events.application.extension.Preferences
import com.marcosrocha85.events.application.extension.getPreferences
import com.marcosrocha85.events.application.toMain
import com.marcosrocha85.events.application.toWelcome

interface SplashViewModel : BaseViewModel {
    val countEnded: MutableLiveData<Boolean>
    fun nextScreen()
    fun startSplash()

    class Factory(context: Context) : BaseViewModel.Factory(context), SplashViewModel {
        override val countEnded = MutableLiveData<Boolean>()

        init {
            countEnded.value = false
        }

        override fun nextScreen() {
            val hasGreetings = context.getPreferences().getBoolean(Preferences.SKIP_WELCOME, false)
            if (hasGreetings) context.toMain()
            context.toWelcome()
        }

        override fun startSplash() {
            val hideHandler = Handler(Looper.myLooper()!!)
            val hideRunnable = Runnable {
                countEnded.value = true
            }
            hideHandler.postDelayed(hideRunnable, 3000)
        }
    }
}