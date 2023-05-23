package com.example.tokotekkotek.view.fragment

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.tokotekkotek.R
import com.example.tokotekkotek.databinding.FragmentHomeBinding
import com.example.tokotekkotek.model.ResponseDataProductItem
import com.example.tokotekkotek.model.ResponseDataSlidersItem
import com.example.tokotekkotek.model.ResponseNewsUpdateItem
import com.example.tokotekkotek.view.adapter.ImageSliderAdapter
import com.example.tokotekkotek.view.adapter.NewProductAdapter
import com.example.tokotekkotek.view.adapter.NewsAdapter
import com.example.tokotekkotek.view.adapter.SecondProductAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    //adapter recyclewview
    private lateinit var imageSliderAdapter: ImageSliderAdapter
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var newProductAdapter: NewProductAdapter
    private lateinit var secondProductAdapter: SecondProductAdapter

    private val list = ArrayList<ResponseDataSlidersItem>()
    private lateinit var dots : ArrayList<TextView>

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

        //percobaan
        newsAdapter = NewsAdapter(arrayListOf(
            ResponseNewsUpdateItem("content", "20 mei", "1","", "Title1"),
            ResponseNewsUpdateItem("content", "20 mei", "1","", "Title2"),
            ResponseNewsUpdateItem("content", "20 mei", "1","", "Title3"),
            ResponseNewsUpdateItem("content", "20 mei", "1","", "Title4"),
            ResponseNewsUpdateItem("content", "20 mei", "1","", "Title5")
        ))

        newProductAdapter = NewProductAdapter(arrayListOf(
            ResponseDataProductItem("1","20 mei", "produk", "1", "Produk 1", 50000, ""),
            ResponseDataProductItem("1","20 mei", "produk", "1", "Produk 2", 50000, ""),
            ResponseDataProductItem("1","20 mei", "produk", "1", "Produk 3", 50000, ""),
            ResponseDataProductItem("1","20 mei", "produk", "1", "Produk 4", 50000, ""),
            ResponseDataProductItem("1","20 mei", "produk", "1", "Produk 5", 50000, "")
        ))

        secondProductAdapter = SecondProductAdapter(arrayListOf(
            ResponseDataProductItem("1","20 mei", "produk", "1", "Produk 1", 50000, ""),
            ResponseDataProductItem("1","20 mei", "produk", "1", "Produk 2", 50000, ""),
            ResponseDataProductItem("1","20 mei", "produk", "1", "Produk 3", 50000, ""),
            ResponseDataProductItem("1","20 mei", "produk", "1", "Produk 4", 50000, ""),
            ResponseDataProductItem("1","20 mei", "produk", "1", "Produk 5", 50000, "")
        ))

        //masih dalam percobaan
        list.add(
            ResponseDataSlidersItem("Mei", "1", "data:image/jpeg;base64,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")
        )
        list.add(
            ResponseDataSlidersItem("Mei", "2",  "https://images.tokopedia.net/img/KRMmCm/2022/5/9/488c5172-8305-4d76-8519-06c7ed915ba6.jpg")
        )

        imageSliderAdapter = ImageSliderAdapter(list)
        binding.pagerSlideImage.adapter = imageSliderAdapter
        dots = ArrayList()
        setIndicator()

        binding.pagerSlideImage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                selectedDot(position)
                super.onPageSelected(position)
            }
        })

        setRecycleViewNews()
        setRecycleViewNewProduct()
        setRecycleViewSecondProduct()
    }

    private fun setRecycleViewSecondProduct() {
        binding.rvSecondProduct.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvSecondProduct.adapter = secondProductAdapter
    }

    private fun setRecycleViewNewProduct() {
        binding.rvNewProduct.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvNewProduct.adapter = newProductAdapter
    }

    private fun setRecycleViewNews() {
        binding.rvNews.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvNews.adapter = newsAdapter
    }

    private fun selectedDot(position: Int) {
        for(i in 0 until list.size){
            if( i == position){
                dots[i].setTextColor(ContextCompat.getColor(requireContext(), androidx.appcompat.R.color.primary_material_light))
            }else{
                dots[i].setTextColor(ContextCompat.getColor(requireContext(), androidx.viewpager2.R.color.secondary_text_default_material_light))
            }
        }
    }

    private fun setIndicator() {
        for (i in 0 until  list.size){
            dots.add(TextView(context))
            dots[i].text = Html.fromHtml("&#9679", Html.FROM_HTML_MODE_LEGACY).toString()
            dots[i].textSize = 10f
            binding.dotIndicatorSlide.addView(dots[i])
        }
    }

}