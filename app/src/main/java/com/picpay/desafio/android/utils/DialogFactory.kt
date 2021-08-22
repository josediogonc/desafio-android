package com.picpay.desafio.android.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.alertDialog(
    title: String,
    message: String, buttonText: String? = "Ok"
) = DialogFactory.showAlertDialog(this, title, message, buttonText)

object DialogFactory {

    fun showAlertDialog(
        appContext: Context, title: String,
        message: String, buttonText: String? = "Ok"
    ) {
        AlertDialog
            .Builder(appContext)
            .apply {
                setTitle(title)
                setMessage(message)
                setPositiveButton(buttonText, null)
                create().show()
            }

    }

}