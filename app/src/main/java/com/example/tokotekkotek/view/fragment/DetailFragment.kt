package com.example.tokotekkotek.view.fragment

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.tokotekkotek.R
import com.example.tokotekkotek.data.UserPreferences
import com.example.tokotekkotek.databinding.FragmentDetailBinding
import com.example.tokotekkotek.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.util.*


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val productViewModel: ProductViewModel by viewModels()
    private lateinit var userPreferences : UserPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        binding.fragmentDetail = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userPreferences = UserPreferences(requireContext().applicationContext)

        setDetailProduct()

        setButtonDetails()

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_loginFragment)
        }
    }

    private fun setButtonDetails() {
        userPreferences.isLoggin().asLiveData().observe(viewLifecycleOwner){
            if(it != null){
                if (it){
                    binding.btnLogin.visibility = View.GONE
                    binding.btnDetailsProduct.visibility = View.VISIBLE
                }else{
                    binding.btnLogin.visibility = View.VISIBLE
                    binding.btnDetailsProduct.visibility = View.GONE
                }
            }
        }
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
    fun convertToRupiah(number: Int): String{

        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number).toString()
    }

    private fun setImage(urlImage : String){
        Glide.with(this)
            .load(urlImage)
            .into(binding.imgDetailProduct)
    }
}
