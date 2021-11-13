package com.example.core.utils.ext

import android.content.Context
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.example.core.domain.model.MovieTv

fun <T> LifecycleOwner.observe(liveData: LiveData<T>?, action: (t: T) -> Unit) {
    liveData?.observe(this,
        { it?.let { t -> action(t) } }
    )
}

fun Context.showToast(msg: String?) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}