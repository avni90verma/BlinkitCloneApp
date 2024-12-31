package com.example.blinkitcloneapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.blinkitcloneapp.databinding.ItemViewProductCategoryBinding
import com.example.blinkitcloneapp.models.Category


class AdapterCategory(
    val categoryList: ArrayList<Category>
) : RecyclerView.Adapter<AdapterCategory.CategoryViewHolder>() {

    // ViewHolder using View Binding
    class CategoryViewHolder(val binding: ItemViewProductCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
       return CategoryViewHolder(ItemViewProductCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
       val category = categoryList[position]
        holder.binding.apply {
            ivCategroyImage.setImageResource(category.image)
            tvCategoryTitle.text = category.title
        }
    }

}