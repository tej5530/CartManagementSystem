package com.cart.dbInterface

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

import com.cart.model.ProductInfo

@Dao
interface DaoAccess {

    @get:Query("SELECT  * FROM ProductInfo ")
    val allProducts: LiveData<List<ProductInfo>>

    @Insert
    fun insertProduct(productInfo: ProductInfo): Long?

    @Query("UPDATE ProductInfo SET fev=:fev WHERE id = :id")
    fun update(fev: Boolean, id: Int)
}
