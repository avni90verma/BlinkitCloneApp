package com.example.blinkitcloneapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.blinkitcloneapp.adapters.AdapterCategory
import com.example.blinkitcloneapp.databinding.FragmentHomeBinding
import com.example.blinkitcloneapp.models.Category

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        setAllCategories()
        return binding.root
    }

    private fun setAllCategories() {

        val categoryList = ArrayList<Category>()

        for (i in Constants.allProductsCategory.indices) {
            categoryList.add(Category(Constants.allProductsCategory[i], Constants.allProductsCategoryIcon[i]))
        }
        binding.rvCategory.adapter = AdapterCategory(categoryList)
    }


}