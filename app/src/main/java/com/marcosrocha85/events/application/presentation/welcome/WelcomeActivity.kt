package com.marcosrocha85.events.application.presentation.welcome

import android.view.View
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.marcosrocha85.events.R
import com.marcosrocha85.events.application.base.BaseActivity
import com.marcosrocha85.events.application.base.BaseFragmentActivity
import com.marcosrocha85.events.application.presentation.welcome.adapter.WelcomePageAdapter
import com.marcosrocha85.events.databinding.ActivityWelcomeBinding

class WelcomeActivity : BaseFragmentActivity<WelcomeViewModel>(), OnPageChangeListener {
    private val viewPagerAdapter = WelcomePageAdapter(this)
    private lateinit var binding: ActivityWelcomeBinding
    override val viewModel = WelcomeViewModel.Factory(this)

    override fun initialize() {
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        binding.viewPager.adapter = viewPagerAdapter
        viewModel.skills.observe(this) {
            viewPagerAdapter.updateSkills(it)
        }
        viewModel.loadSkills()
    }

    override fun getInflatedLayout(): View {
        return ActivityWelcomeBinding.inflate(layoutInflater).root
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