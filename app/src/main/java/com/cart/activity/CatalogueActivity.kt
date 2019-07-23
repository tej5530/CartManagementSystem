package com.cart.activity

import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

import com.cart.R
import com.cart.adapter.CatalogAdapter

class CatalogueActivity : AppCompatActivity() {

    private var rvListing: RecyclerView? = null
    private var floatingActionButton: FloatingActionButton? = null
    private var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        castingControl()
        eventManagement()
        generalTask()
    }

    private fun castingControl() {
        rvListing = findViewById(R.id.rvListing)
        floatingActionButton = findViewById(R.id.floatingActionButton)
    }

    private fun eventManagement() {

    }

    private fun generalTask() {
        rvListing!!.layoutManager = GridLayoutManager(context, 2)
        rvListing!!.adapter = context?.let { CatalogAdapter(it) }
    }

}
