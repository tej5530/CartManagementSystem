package com.cart.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cart.activity.ProductActivity


class CatalogAdapter(private val context: Context) : RecyclerView.Adapter<CatalogAdapter.CatalogHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): CatalogHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(com.cart.R.layout.row_catalogue, parent, false)
        return CatalogHolder(itemView)
    }

    override fun onBindViewHolder(catalogHolder: CatalogHolder, i: Int) {
        displayHeightWidth(catalogHolder.llMain, 2)
    }

    override fun getItemCount(): Int {
        return 20
    }

    inner class CatalogHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val llMain: ConstraintLayout

        init {
            llMain = itemView.findViewById(com.cart.R.id.llMain)
            llMain.setOnClickListener {
                val intent = Intent(context, ProductActivity::class.java)
                context.startActivity(intent)
            }
        }
    }

    private fun displayHeightWidth(convertedView: View, part: Int) {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        val deviceHeight = displayMetrics.heightPixels
        val layoutParams = convertedView.layoutParams
        val valueInPixels = 0
        layoutParams.height = deviceHeight / part
        if (convertedView.layoutParams is ViewGroup.MarginLayoutParams) {
            val p = convertedView.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(valueInPixels, valueInPixels, valueInPixels, valueInPixels)
            convertedView.requestLayout()
        }
    }
}
