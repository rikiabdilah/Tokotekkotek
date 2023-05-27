package com.example.tokotekkotek.view.fragment

import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.tokotekkotek.R
import com.example.tokotekkotek.data.UserPreferences
import com.example.tokotekkotek.databinding.FragmentHomeBinding
import com.example.tokotekkotek.view.adapter.ImageSliderAdapter
import com.example.tokotekkotek.view.adapter.NewProductAdapter
import com.example.tokotekkotek.view.adapter.NewsAdapter
import com.example.tokotekkotek.view.adapter.SecondProductAdapter
import com.example.tokotekkotek.viewmodel.NewsViewModel
import com.example.tokotekkotek.viewmodel.ProductViewModel
import com.example.tokotekkotek.viewmodel.SlidersViewModel
import com.example.tokotekkotek.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    //adapter recycleview
    private lateinit var imageSliderAdapter: ImageSliderAdapter
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var newProductAdapter: NewProductAdapter
    private lateinit var secondProductAdapter: SecondProductAdapter

    //viewModel initial
    private val userViewModel : UserViewModel by viewModels()
    private val newsViewModel : NewsViewModel by viewModels()
    private val productViewModel : ProductViewModel by viewModels()
    private val slidersViewModel : SlidersViewModel by viewModels()

    //sliders
    private lateinit var dots : ArrayList<TextView>
    private var listSizeSliders : Int = 0

    //nav
    private lateinit var navController: NavController

    //dspref
    private lateinit var userPreferences: UserPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.getDataAllUsers()

        //dspref
        userPreferences = UserPreferences(requireContext().applicationContext)

        userViewModel.users.observe(viewLifecycleOwner){
            if(it != null){
                Log.d("HASIL_USER", it.toString())
            }
        }

        userPreferences.idUser.asLiveData().observe(viewLifecycleOwner){
            Log.i("HASIL_ID_USER", it.toString())
        }

        newsAdapter = NewsAdapter(ArrayList())
        imageSliderAdapter = ImageSliderAdapter(ArrayList())
        newProductAdapter = NewProductAdapter(ArrayList())
        secondProductAdapter = SecondProductAdapter(ArrayList())


        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

        binding.btnCart.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_keranjangFragment)
        }

        binding.pagerSlideImage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                selectedDot(position)
                super.onPageSelected(position)
            }
        })

        setRecycleViewNews()
        setRecycleViewNewProduct()
        setRecycleViewSecondProduct()
        setViewPagerSliders()
    }

    private fun setViewPagerSliders() {
        slidersViewModel.getDataAllSliders()

        binding.pagerSlideImage.adapter = imageSliderAdapter

        dots = ArrayList()

        slidersViewModel.allSliders.observe(viewLifecycleOwner){
            if(it != null){
                imageSliderAdapter.setDataListSlider(it)
                listSizeSliders = it.size
                setIndicator(it.size)

            }
        }
    }

    private fun setRecycleViewSecondProduct() {
        binding.rvSecondProduct.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = secondProductAdapter
        }

        productViewModel.getDataAllProduct(2)

        productViewModel.allSecondProduct.observe(viewLifecycleOwner){
            if(it != null){
                secondProductAdapter.setDataListSecondProduct(it)
            }
        }

        secondProductAdapter.onClickItemSecondProduct = {
            if(it.idProduct.isNotEmpty()){
                val bundleProduct = Bundle().apply {
                    putInt("ID_CATEGORY_PRODUCT", it.categoryProductId.toInt())
                    putInt("ID_PRODUCT", it.idProduct.toInt())
                }

               findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundleProduct)
            }
        }
    }

    private fun setRecycleViewNewProduct() {
        binding.rvNewProduct.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = newProductAdapter
        }

        productViewModel.getDataAllProduct(1)

        productViewModel.allNewProduct.observe(viewLifecycleOwner){
            if(it != null){
                newProductAdapter.setDataListNewProduct(it)
            }
        }

        newProductAdapter.onClickItemNewProduct = {
            if(it.idProduct.isNotEmpty()){
                val bundleProduct = Bundle().apply {
                    putInt("ID_CATEGORY_PRODUCT", it.categoryProductId.toInt())
                    putInt("ID_PRODUCT", it.idProduct.toInt())
                }

                findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundleProduct)
            }
        }
    }

    private fun setRecycleViewNews() {
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = newsAdapter
        }

        newsViewModel.getDataAllNewsUpdate()

        newsViewModel.allNewsUpdate.observe(viewLifecycleOwner){
            if(it != null){
                newsAdapter.setDataNews(it)
            }
        }

        newsAdapter.onClickItemNews = {
            if(it.idNews.isNotEmpty()){
                val bundleNews = Bundle().apply {
                    putInt("ID_NEWS", it.idNews.toInt())
                }
                findNavController().navigate(R.id.action_homeFragment_to_detailNewsFragment, bundleNews)
            }
        }

    }

    private fun selectedDot(position: Int) {
        for(i in 0 until listSizeSliders){
            if( i == position){
                dots[i].setTextColor(ContextCompat.getColor(requireContext(), androidx.appcompat.R.color.primary_material_light))
            }else{
                dots[i].setTextColor(ContextCompat.getColor(requireContext(), androidx.viewpager2.R.color.secondary_text_default_material_light))
            }
        }
    }

    private fun setIndicator(sizeList : Int) {
        binding.dotIndicatorSlide.removeAllViews()
        for (i in 0 until  sizeList){
            dots.add(TextView(context))
            dots[i].text = Html.fromHtml("&#9679", Html.FROM_HTML_MODE_LEGACY).toString()
            dots[i].textSize = 12f
            binding.dotIndicatorSlide.addView(dots[i])
        }
    }

}