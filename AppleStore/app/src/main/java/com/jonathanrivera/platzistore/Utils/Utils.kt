package com.jonathanrivera.platzistore.Utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.Toast

fun Activity.toast(mensaje: String) {
    Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
}

fun ViewGroup.inflate(layoutId: Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}