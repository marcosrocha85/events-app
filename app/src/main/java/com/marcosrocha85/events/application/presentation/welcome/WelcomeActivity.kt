package com.marcosrocha85.events.application.presentation.welcome

import android.view.LayoutInflater
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.marcosrocha85.events.R
import com.marcosrocha85.events.application.base.BaseActivity
import com.marcosrocha85.events.application.presentation.welcome.adapter.WelcomePageAdapter
import com.marcosrocha85.events.databinding.ActivityWelcomeBinding

class WelcomeActivity : BaseActivity<WelcomeViewModel>(), OnPageChangeListener {
    private val viewPagerAdapter = WelcomePageAdapter(this)
    private val binding = ActivityWelcomeBinding.inflate(LayoutInflater.from(this))
    override val viewModel = WelcomeViewModel.Factory()
    override val containerView = R.layout.activity_welcome

    override fun initialize() {
        viewModel.skills.observe(this) {
            viewPagerAdapter.updateSkills(it)
        }
        viewModel.loadSkills()
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        when (position) {
            0 -> {
                binding.buttonNext.setText(R.string.greetings_button_next)
                binding.buttonBack.setText(R.string.greetings_button_skip)
            }
            viewPagerAdapter.itemCount - 1 -> {
                binding.buttonNext.setText(R.string.greetings_button_start)
                binding.buttonBack.setText(R.string.greetings_button_back)
            }
            else -> {
                binding.buttonNext.setText(R.string.greetings_button_next)
                binding.buttonBack.setText(R.string.greetings_button_back)
            }
        }
    }

    override fun onPageSelected(position: Int) { }

    override fun onPageScrollStateChanged(state: Int) { }
}