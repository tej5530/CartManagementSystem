package com.cart.dataBase

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Room
import android.content.Context
import android.os.AsyncTask

import com.cart.model.ProductInfo

class CartRepository(context: Context) {
    private val DB_NAME = "db_task"
    private val cartDatabase: CartDataBase

    val tasks: LiveData<List<ProductInfo>>
        get() = cartDatabase.daoAccess().allProducts

    init {
        cartDatabase = Room.databaseBuilder(context, CartDataBase::class.java, DB_NAME).build()
    }

    fun insertTask(productInfo: ProductInfo) {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                cartDatabase.daoAccess().insertProduct(productInfo)
                return null
            }
        }.execute()
    }

    fun updateFev(isFev: Boolean, id: Int) {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                cartDatabase.daoAccess().update(isFev, id)
                return null
            }
        }.execute()
    }

    fun addCart() {

    }
}
