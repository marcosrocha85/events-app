package com.marcosrocha85.events.application.presentation.splash

import android.annotation.SuppressLint
import com.marcosrocha85.events.R
import com.marcosrocha85.events.application.base.BaseActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : BaseActivity<SplashViewModel>() {
    override val viewModel = SplashViewModel.Factory()
    override val containerView = R.layout.activity_splash_screen

    override fun initialize() {
        viewModel.countEnded.observe(this) {
            if (it) {
                viewModel.nextScreen()
            }
        }
        viewModel.startSplash()
    }
}