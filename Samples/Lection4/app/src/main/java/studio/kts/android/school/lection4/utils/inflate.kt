package studio.kts.android.school.lection4.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}


fun <T> ViewGroup.bindingInflate(
    inflate: (LayoutInflater, ViewGroup?, Boolean) -> T,
    attachToParent: Boolean = false
): T {
    return inflate(
        LayoutInflater.from(context),
        this,
        attachToParent
    )
}