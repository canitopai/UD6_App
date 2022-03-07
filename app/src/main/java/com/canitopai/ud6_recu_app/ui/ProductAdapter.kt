package com.canitopai.ud6_recu_app.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.canitopai.ud6_recu_app.R
import com.canitopai.ud6_recu_app.data.model.ProductItem
import com.canitopai.ud6_recu_app.databinding.ProductItemBinding
import com.squareup.picasso.Picasso


class ProductAdapter(private val onProductClicked: (ProductItem) -> Unit) :
    ListAdapter<ProductItem, ProductAdapter.ViewHolder>(ProductItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ProductItemBinding = ProductItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val prod = getItem(position)
        if (prod.stock <= 5){
            holder.binding.tvWarn.visibility = View.VISIBLE
        }
        holder.binding.tvName.text = prod.name
        holder.binding.tvPrice.text = prod.discountPrice.toString()
        holder.binding.tvDesc.text = prod.description

        Picasso.get()
            .load(prod.imageUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.binding.imageView)


        holder.binding.root.setOnClickListener {
            onProductClicked(prod)
        }
    }

    inner class ViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root)


}

class ProductItemCallback : DiffUtil.ItemCallback<ProductItem>() {
    override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: ProductItem,
        newItem: ProductItem
    ): Boolean = oldItem.id == newItem.id

}

