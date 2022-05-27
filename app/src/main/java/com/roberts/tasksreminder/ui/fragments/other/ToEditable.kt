package com.roberts.tasksreminder.ui.fragments.other

import android.content.Context
import android.text.Editable

interface ToEditable {
    fun String.toEditable(): Editable? {
        return Editable.Factory.getInstance().newEditable(this)
    }

    fun show(applicationContext: Context?, tag: String?)
}