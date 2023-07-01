package com.marcosrocha85.events.application.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.transition.TransitionManager
import com.marcosrocha85.events.R
import com.marcosrocha85.events.application.utils.ViewStatus

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity(), BaseView<VM> {
    protected open val constraintRoot = R.id.constraint_root
    abstract override val viewModel: VM?
    abstract val containerView: Int
    abstract fun initialize()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(containerView)
        viewModel?.viewStatus?.observe(this, Observer<ViewStatus> {
            if (it.isLoading) {
                showLoading()
            } else {
                hideLoading()
            }
        })
        initialize()
    }

    protected open val loadingView: LinearLayout by lazy {
        val layout = LinearLayout(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            layout.elevation = 999f
        }
        layout.id = R.id.loading_view
        layout.orientation = LinearLayout.VERTICAL
        layout.gravity = Gravity.CENTER
        layout.setBackgroundColor(ContextCompat.getColor(this, androidx.appcompat.R.color.primary_material_light))
        layout.isClickable = true

        val progress = ProgressBar(this)
        progress.isIndeterminate = true

        layout.addView(
            progress,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )

        val message = TextView(this, null, androidx.appcompat.R.style.Base_TextAppearance_AppCompat)
        message.setText(R.string.loading_data)
        message.setTextColor(ContextCompat.getColor(this, androidx.appcompat.R.color.primary_text_default_material_light))
        message.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.text_size_medium))

        layout.addView(
            message,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )

        return@lazy layout
    }

    open fun showLoading() {
        val rootView: View? = findViewById(constraintRoot)
        if (rootView !is ConstraintLayout) {
            return
        }

        val root: ConstraintLayout = rootView
        if (loadingView.isAttachedToWindow) {
            return
        }

        this.currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
        loadingView.alpha = 0f
        loadingView.bringToFront()

        root.addView(
            loadingView,
            ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
        )
        val constraint = ConstraintSet()
        constraint.apply {
            this.connect(
                loadingView.id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP
            )
            this.connect(
                loadingView.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START
            )
            this.connect(
                loadingView.id,
                ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM
            )
            this.connect(
                loadingView.id,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END
            )
        }
        constraint.setAlpha(loadingView.id, 0.7f)

        TransitionManager.beginDelayedTransition(root)
        constraint.applyTo(root)
    }

    open fun hideLoading() {
        val root: ConstraintLayout? = findViewById(constraintRoot)
        root?.removeView(loadingView)
    }
}