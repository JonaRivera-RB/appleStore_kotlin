package com.jonathanrivera.platzistore.MainView

import android.app.Activity
import android.content.Intent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jonathanrivera.platzistore.Utils.inflate
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.jonathanrivera.platzistore.Data.Model.ProductModel
import kotlinx.android.synthetic.main.item_landing.view.*
import com.jonathanrivera.platzistore.DetailActivity
import com.jonathanrivera.platzistore.R


class AdapterLanding(val data:List<ProductModel>): RecyclerView.Adapter<AdapterLanding.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(parent.inflate(R.layout.item_landing))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindView(data[position])
    }


    class Holder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bindView(itemlanding: ProductModel)  {
            with(itemlanding) {

                itemView.titleLabel.text = title
                itemView.priceLabel.text = price

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra("title", title)
                    intent.putExtra("description", description)
                    intent.putExtra("price", price)

                    val p1: androidx.core.util.Pair<View, String> = androidx.core.util.Pair(itemView.itemImage, "imageTransition")
                    val p2: androidx.core.util.Pair<View, String> = androidx.core.util.Pair(itemView.titleLabel, "titleTransition")
                    val p4: androidx.core.util.Pair<View, String> = androidx.core.util.Pair(itemView.priceLabel, "priceTransition")

                    val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(itemView.context as Activity, p1, p2, p4)
                    itemView.context.startActivity(intent, options.toBundle())
                }
            }
        }
    }
}