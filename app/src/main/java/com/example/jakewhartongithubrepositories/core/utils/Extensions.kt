package com.example.jakewhartongithubrepositories.core.utils

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

fun View.showSnack(message: String) {
    Snackbar.make(
        this, message, Snackbar.LENGTH_LONG
    ).show()
}

fun Context.showDialog(
    title: String,
    desc: String,
    positiveButtonText: String,
    positiveButtonAction: () -> Unit,
    negativeButtonText: String,
    negativeButtonAction: () -> Unit,
) {
    val builder = AlertDialog.Builder(this)
    builder.setTitle(title)
    builder.setMessage(desc)

    builder.setPositiveButton(positiveButtonText) { dialog, _ ->
        dialog.dismiss()
        positiveButtonAction.invoke()
    }

    builder.setNegativeButton(negativeButtonText) { dialog, _ ->
        dialog.dismiss()
        negativeButtonAction.invoke()
    }
    builder.show()
}