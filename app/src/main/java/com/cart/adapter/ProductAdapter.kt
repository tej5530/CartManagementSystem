package com.cart.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cart.R
import com.cart.dataBase.CartRepository
import com.cart.model.ProductInfo

abstract class ProductAdapter(private val context: Context, private val list: List<ProductInfo>?) : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {
    private var cartRepository: CartRepository? = null

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ProductHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_product, parent, false)
        return ProductHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(catalogHolder: ProductHolder, @SuppressLint("RecyclerView") i: Int) {
        cartRepository = CartRepository(context)
        Glide.with(context).load(list!![i].imgUrl)
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(catalogHolder.ivProduct)
        catalogHolder.tvProductName.text = list[i].productName
        catalogHolder.tvDescription.text = list[i].productDescription
        catalogHolder.etQty.setText("" + list[i].qyt)
        catalogHolder.etQty.isEnabled = false
        catalogHolder.tvPrice.text = "Price :  \u20B9" + list[i].price * list[i].qyt
        catalogHolder.ivMinus.setOnClickListener { catalogHolder.etQty.setText("" + qtyMinus(list[i].qyt, list[i].price, i, catalogHolder.tvPrice)) }
        catalogHolder.ivPlus.setOnClickListener { catalogHolder.etQty.setText("" + qtyPlus(list[i].qyt, list[i].price, i, catalogHolder.tvPrice)) }
        catalogHolder.btnAddToCart.setOnClickListener {
            /* Here you call your cart api to fill item in to cart or else manage through Local data base*/
            val productInfo = list[i]
            onAddToCart(productInfo)
            System.currentTimeMillis()
        }
        setFav(context, list[i].isWish, catalogHolder.ivFev)
        catalogHolder.ivFev.setOnClickListener {
            if (list[i].isWish) {
                list[i].isWish = false
                /* used for update the fav state in database*/
                cartRepository!!.updateFev(false, list[i].id)
                setFav(context, list[i].isWish, catalogHolder.ivFev)
            } else {
                list[i].isWish = true
                cartRepository!!.updateFev(true, list[i].id)
                setFav(context, list[i].isWish, catalogHolder.ivFev)
            }
        }
    }

    /* this method is used for set heart image for fav product*/
    private fun setFav(context: Context, wish: Boolean, ivFev: ImageView) {
        if (wish) {
            ivFev.setImageDrawable(context.resources.getDrawable(R.drawable.ic_heart_fill))
        } else {
            ivFev.setImageDrawable(context.resources.getDrawable(R.drawable.ic_favorite))
        }
    }

    /* this function is used for minus calculation*/
    @SuppressLint("SetTextI18n")
    private fun qtyMinus(qty: Int, price: Int, position: Int, tvPrice: TextView): Int {
        var qty = qty
        --qty
        list!![position].qyt = qty
        tvPrice.text = context.getString(R.string.price) + qty * price
        return qty
    }

    /* this function is used for plus calculation*/
    @SuppressLint("SetTextI18n")
    private fun qtyPlus(qty: Int, price: Int, position: Int, tvPrice: TextView): Int {
        var qty = qty
        ++qty
        list!![position].qyt = qty
        tvPrice.text = context.getString(R.string.price) + qty * price
        return qty
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    abstract fun onAddToCart(productInfo: ProductInfo)

    inner class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivProduct: ImageView
        val tvProductName: TextView
        val tvDescription: TextView
        val tvPrice: TextView
        val ivMinus: ImageView
        val etQty: EditText
        val ivPlus: ImageView
        val ivFev: ImageView
        val btnAddToCart: Button
        private val llMain: ConstraintLayout

        init {
            llMain = itemView.findViewById(R.id.llMain)
            ivProduct = itemView.findViewById(R.id.ivProduct)
            tvProductName = itemView.findViewById(R.id.tvProductName)
            tvDescription = itemView.findViewById(R.id.tvDescription)
            tvPrice = itemView.findViewById(R.id.tvPrice)
            ivMinus = itemView.findViewById(R.id.ivMinus)
            etQty = itemView.findViewById(R.id.etQty)
            ivPlus = itemView.findViewById(R.id.ivPlus)
            ivFev = itemView.findViewById(R.id.ivFev)
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart)
        }
    }
}
