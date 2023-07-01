package com.marcosrocha85.events.application.extension

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Space
import androidx.core.content.ContextCompat
import com.marcosrocha85.events.BuildConfig
import com.marcosrocha85.events.R

object Preferences {
    const val SKIP_WELCOME = "skip_welcome"
}

fun Context.dimensionFromAttribute(attribute: Int): Int {
    val attributes = obtainStyledAttributes(intArrayOf(attribute))
    val dimension = attributes.getDimensionPixelSize(0, 0)
    attributes.recycle()
    return dimension
}

fun Context.getPreferences(prefName: String? = null): SharedPreferences {
    return this.getSharedPreferences(
        if (prefName != null) prefName + "_PREFERENCES"
        else BuildConfig.APPLICATION_ID + "_PREFERENCES",
        Context.MODE_PRIVATE
    )
}

fun Context.colorFromResId(resId: Int): Int {
    return ContextCompat.getColor(this, resId)
}

fun Context.addLayout(v: View, llParent: ViewGroup) {
    if (llParent.childCount > 0) {
        val space = Space(this)
        llParent.addView(
            space,
            llParent.childCount,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                resources.getDimensionPixelSize(R.dimen.margin_padding_size_small)
            )
        )
    }
    llParent.addView(v, llParent.childCount)
}