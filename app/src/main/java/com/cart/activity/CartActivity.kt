package com.cart.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.widget.Button
import com.cart.R
import com.cart.model.ProductInfo
import java.util.*

class CartActivity : AppCompatActivity() {

    private var btnPlaceOrder: Button? = null
    private var rvCart: RecyclerView? = null
    private var listIntent: ArrayList<ProductInfo>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        castingControl()
        eventManagement()
        generalTask()
        val intent = Intent(this, ProductActivity::class.java)
        startActivity(intent)
    }

    private fun castingControl() {
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder)
        rvCart = findViewById(R.id.rvCart)
    }

    private fun eventManagement() {}

    private fun generalTask() {
        listIntent = intent.getSerializableExtra("obj") as ArrayList<ProductInfo>
    }
}
