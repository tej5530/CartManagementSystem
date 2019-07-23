package com.cart.dataBase

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

import com.cart.dbInterface.DaoAccess
import com.cart.model.ProductInfo

@Database(entities = [ProductInfo::class], version = 1, exportSchema = false)
abstract class CartDataBase : RoomDatabase() {
    abstract fun daoAccess(): DaoAccess
}
