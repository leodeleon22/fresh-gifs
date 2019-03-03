package com.leodeleon.freshgifs.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.leodeleon.freshgifs.app.App

val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Any.logd(msg:String, tag: String = this.javaClass.simpleName){
    Log.d(tag,msg)
}

fun Any.getString(@StringRes resId: Int): String {
    return App.instance.getString(resId)
}

fun View.snack(@StringRes resId: Int, length: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this , resId , length).show()
}

fun Context.toast(@StringRes resId: Int, length: Int = Toast.LENGTH_LONG){
    Toast.makeText(this,resId,length).show()
}

private val animStub: (Animation) -> Unit = {}
fun Animation.listen(onEnd: (Animation) -> Unit = animStub) {
    setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {}

        override fun onAnimationRepeat(animation: Animation) {}

        override fun onAnimationEnd(animation: Animation) {
            onEnd(animation)
        }
    })
}

fun TabLayout.listen(onSelected: (TabLayout.Tab) -> Unit = {}){
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab) { }

        override fun onTabUnselected(tab: TabLayout.Tab) { }

        override fun onTabSelected(tab: TabLayout.Tab) {
            onSelected(tab)
        }
    })
}