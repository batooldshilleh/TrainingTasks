package com.example.tasks.fragmentAPP



import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.tasks.R

fun Button.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = View.OnClickListener {
        if (System.currentTimeMillis() - (it.getTag(R.id.btnLastClickTime) as? Long ?: 0) > 1000) {
            it.setTag(R.id.btnLastClickTime, System.currentTimeMillis())
            onSafeClick(it)
        }
    }
    setOnClickListener(safeClickListener)
}


fun TextView.showArgument(arguments: Bundle?, key: String, default: String = "") {
    val value = arguments?.getString(key, default)
    text = value
}


fun Fragment.navigateToFragment(fragment: Fragment, tag: String) {
    requireActivity().supportFragmentManager.beginTransaction().apply {
        setCustomAnimations(
            R.anim.slide_in_right,
            R.anim.slide_in_left
        )
        replace(R.id.fcvFragmints, fragment)
        addToBackStack(tag)
        setReorderingAllowed(true)
        commit()
    }
}
