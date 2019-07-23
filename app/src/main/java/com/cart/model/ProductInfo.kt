package com.cart.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

import java.io.Serializable


@Entity
class ProductInfo
//    public ProductInfo(int price, int qyt) {
//        this.price = price;
//        this.qyt = qyt;
//    }


(@field:ColumnInfo(name = "price")
 var price: Int, @field:ColumnInfo(name = "qty")
 var qyt: Int, @field:ColumnInfo(name = "pname")
 var productName: String, @field:ColumnInfo(name = "pdesc")
 var productDescription: String, @field:ColumnInfo(name = "url")
 var imgUrl: String, isWish: Boolean) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @ColumnInfo(name = "fev")
    var isWish = false

    init {
        this.isWish = isWish
    }

}
