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

import com.cart.R
import com.cart.activity.ProductActivity

class CartAdapter(private val context: Context) : RecyclerView.Adapter<CartAdapter.CartHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): CartHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_catalogue, parent, false)
        return CartHolder(itemView)
    }

    override fun onBindViewHolder(cartHolder: CartHolder, i: Int) {
        displayHeightWidth(cartHolder.llMain, 2)
    }

    override fun getItemCount(): Int {
        return 20
    }

    inner class CartHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val llMain: ConstraintLayout

        init {
            llMain = itemView.findViewById(R.id.llMain)
            llMain.setOnClickListener { context.startActivity(Intent(context, ProductActivity::class.java)) }
        }
    }

    private fun displayHeightWidth(convertedView: View, part: Int) {
        val displaymetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displaymetrics)
        val deviceHeight = displaymetrics.heightPixels
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