package com.picpay.desafio.android.utils

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.toast(text : String) = Toast.makeText(this, text, Toast.LENGTH_LONG).show()
