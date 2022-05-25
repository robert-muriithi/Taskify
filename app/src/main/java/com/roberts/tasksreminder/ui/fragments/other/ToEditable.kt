package com.roberts.tasksreminder.ui.fragments.other

import android.text.Editable

interface ToEditable {
    fun String.toEditable(): Editable? {
        return Editable.Factory.getInstance().newEditable(this)
    }
}