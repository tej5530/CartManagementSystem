package com.cart.activity

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.cart.R
import com.cart.adapter.ProductAdapter
import com.cart.dataBase.CartRepository
import com.cart.model.ProductInfo
import java.util.*

class ProductActivity : AppCompatActivity(), View.OnClickListener {

    private var context: Context? = null
    private var rvProduct: RecyclerView? = null
    private var list: ArrayList<ProductInfo>? = null
    private var listIntent: ArrayList<ProductInfo>? = null
    private var cartRepository: CartRepository? = null
    private var flProceed: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        context = this
        castingControl()
        eventManagement()
        generalTask()
    }

    private fun castingControl() {
        rvProduct = findViewById(R.id.rvProduct)
        flProceed = findViewById(R.id.flProceed)
    }

    private fun eventManagement() {
        flProceed!!.setOnClickListener(this)
    }

    private fun generalTask() {
        rvProduct!!.layoutManager = LinearLayoutManager(context)
        fillData()
    }

    private fun fillData(): ArrayList<ProductInfo>? {
        //        list = new ArrayList<>();
        listIntent = ArrayList()
        cartRepository = context?.let { CartRepository(it) }
        cartRepository!!.insertTask(ProductInfo(10, 56, "Headphone", "Best sound quality", "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1100&q=60", false))
        cartRepository!!.insertTask(ProductInfo(20, 80, "Smart watch", "Watch", "https://images.unsplash.com/photo-1523275335684-37898b6baf30?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1100&q=60", false))
        cartRepository!!.insertTask(ProductInfo(30, 70, "Apple Airpod", "Airpod", "https://images.unsplash.com/photo-1504274066651-8d31a536b11a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1100&q=60", false))
        cartRepository!!.insertTask(ProductInfo(40, 20, "Bag", "Travel Bag", "https://images.unsplash.com/photo-1491637639811-60e2756cc1c7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=200&q=60", false))
        cartRepository!!.insertTask(ProductInfo(50, 60, "iPhone", "Best Phone", "https://images.unsplash.com/photo-1542545073-8768795964d6?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=60", false))
        cartRepository!!.insertTask(ProductInfo(60, 50, "Camera", "DSLR Camera", "https://images.unsplash.com/photo-1469210537992-30c8c8abef12?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=60", false))

        cartRepository!!.tasks.observe((context as LifecycleOwner?)!!, Observer { productInfos ->
            list = ArrayList()
            list = productInfos as ArrayList<ProductInfo>?
            rvProduct!!.adapter = object : ProductAdapter(context!!, productInfos) {
                override fun onAddToCart(productInfo: ProductInfo) {
                    if (listIntent != null) {
                        for (i in listIntent!!.indices) {
                            if (productInfo.id != listIntent!![i].id) {
                                listIntent!!.add(productInfo)
                            }
                        }
                    } else {
                        listIntent!!.add(productInfo)
                    }
                }
            }
            Log.e("fillData: ", "" + list!!.size)
        })
        return list
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.flProceed -> {
                val intent = Intent(context, CartActivity::class.java)
                intent.putExtra("obj", listIntent)
                startActivity(intent)
            }
        }//                Toast.makeText(ProductActivity.this, "Added "+ productInfo.getPrice(), Toast.LENGTH_SHORT).show();
    }
}
