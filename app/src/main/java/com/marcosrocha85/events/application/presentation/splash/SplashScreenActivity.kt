package com.marcosrocha85.events.application.presentation.splash

import android.annotation.SuppressLint
import android.view.View
import com.marcosrocha85.events.R
import com.marcosrocha85.events.application.base.BaseActivity
import com.marcosrocha85.events.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : BaseActivity<SplashViewModel>() {
    override val viewModel = SplashViewModel.Factory(this)

    override fun initialize() {
        supportActionBar?.hide()
        viewModel.countEnded.observe(this) {
            if (it) {
                viewModel.nextScreen()
            }
        }
        viewModel.startSplash()
    }

    override fun getInflatedLayout(): View {
        return ActivitySplashScreenBinding.inflate(layoutInflater).root
    }
}