package com.jonathanrivera.platzistore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_description.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent?.extras?.let {
            productNameDetailTxt.text = it.getString("title")
            productDescriptionDetailTxt.text = it.getString("description")
            productPriceDetailTxt.text = it.getString("price")
        }
    }
}
