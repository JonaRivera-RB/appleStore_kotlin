package com.jonathanrivera.platzistore.MainView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.jonathanrivera.platzistore.Data.Model.DBOpenHelper
import com.jonathanrivera.platzistore.Data.Model.ProductModel
import com.jonathanrivera.platzistore.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shoesRecyclerView.layoutManager = GridLayoutManager(this, 2)

        val watches = arrayListOf(
            ProductModel("Apple Watch series 5","Caja de acero inoxidable color oro • Correa estilo milanés","Desde $18,999", R.drawable.dos),
            ProductModel("Apple Watch series 5","Caja de acero inoxidable • Correa estilo milanés","Desde $18,999",R.drawable.siete),
            ProductModel("Apple Watch series 5","Caja de acero inoxidable color oro • Correa deportiva","Desde $17,999",R.drawable.cuatro),
            ProductModel("Apple Watch series 5","Caja de acero inoxidable color gris espacial • Correa deportiva","Desde $9,999",R.drawable.tres),
            ProductModel("Apple Watch series 5","Caja de acero inoxidable color gris espacial • Correa deportiva","Desde $10,799",R.drawable.seis),
            ProductModel("Apple Watch series 5","Caja de acero inoxidable color plata • Correa deportiva","Desde $9,999",R.drawable.cinco)
        )

        watches.forEach {
            insertProduct(it)
        }

        val adapter = AdapterLanding(watches)
        shoesRecyclerView.adapter = adapter
    }

    private fun insertProduct(productModel: ProductModel){
        var dataBase = DBOpenHelper(this)
        dataBase.insertProduct(productModel)
    }

    override fun onDestroy() {
        super.onDestroy()

        val dataBase = DBOpenHelper(this)
        dataBase.clearProducts()
    }
}
