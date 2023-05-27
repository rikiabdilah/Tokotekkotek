package com.example.tokotekkotek.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.tokotekkotek.R
import com.example.tokotekkotek.databinding.FragmentDetailBinding
import com.example.tokotekkotek.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val productViewModel: ProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDetailProduct()
    }

    private fun setDetailProduct() {
        val getIdProduct = arguments?.getInt("ID_PRODUCT")
        val getIdCategory = arguments?.getInt("ID_CATEGORY_PRODUCT")

        if (getIdProduct != null && getIdCategory != null) {
            productViewModel.getDetailDataProduct(getIdCategory, getIdProduct)

        }

        if (getIdCategory != null) {
            if (getIdCategory == 1) {
                productViewModel.detailNewProduct.observe(viewLifecycleOwner) {
                    binding.detailProduct = it
                    setImage(it.productImage)
                }
            } else {
                productViewModel.detailSecondProduct.observe(viewLifecycleOwner) {
                    binding.detailProduct = it
                    setImage(it.productImage)
                }
            }

        }
    }

    private fun setImage(urlImage : String){
        Glide.with(this)
            .load(urlImage)
            .into(binding.imgDetailProduct)
    }
}
