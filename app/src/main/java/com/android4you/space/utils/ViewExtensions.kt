package com.android4you.space.utils

import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.style.BulletSpan
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

fun View.color(@ColorRes resource: Int): Int {
    return context.color(resource)
}

fun View.drawable(@DrawableRes resource: Int): Drawable? {
    return context.drawable(resource)
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun List<String>.toBulletedList(): CharSequence {
    return SpannableString(this.joinToString("\n")).apply {
        this@toBulletedList.foldIndexed(0) { index, acc, span ->
            val end = acc + span.length + if (index != this@toBulletedList.size - 1) 1 else 0
            this.setSpan(BulletSpan(16), acc, end, 0)
            end
        }
    }
}
