package com.marcosrocha85.events.application

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.marcosrocha85.events.application.presentation.home.MainActivity
import com.marcosrocha85.events.application.presentation.welcome.WelcomeActivity

fun Context.toMain() {
    this.push(MainActivity::class.java, flags = Intent.FLAG_ACTIVITY_CLEAR_TOP)
}

fun Context.toWelcome() {
    this.push(WelcomeActivity::class.java, flags = Intent.FLAG_ACTIVITY_CLEAR_TOP)
}

private fun Context.push(
    clazz: Class<*>,
    extras: Bundle? = null,
    flags: Int? = null,
    options: Bundle? = null
) {
    val intent = Intent(this, clazz)
    extras?.let {
        intent.putExtras(it)
    }
    flags?.let {
        intent.setFlags(it)
    }
    ContextCompat.startActivity(this, intent, options)
}

private fun Activity.pushToResult(clazz: Class<*>, requestCode: Int, extras: Bundle? = null) {
    val intent = Intent(this, clazz)
    extras?.let {
        intent.putExtras(it)
    }
    this.startActivityForResult(intent, requestCode)
}
