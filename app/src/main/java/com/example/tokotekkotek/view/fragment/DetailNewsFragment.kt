package com.example.tokotekkotek.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.tokotekkotek.databinding.FragmentDetailNewsBinding
import com.example.tokotekkotek.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailNewsFragment : Fragment() {

    private lateinit var binding : FragmentDetailNewsBinding
    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDetailNews()
    }

    private fun setDetailNews() {
        val getIdNews = arguments?.getInt("ID_NEWS")

        if(getIdNews != null){
            newsViewModel.getDetailDataNewsUpdate(getIdNews)
        }

        newsViewModel.detailNewsUpdate.observe(viewLifecycleOwner){
            if(it.idNews.isNotEmpty()){
                binding.detailNews = it

                Glide.with(this)
                    .load(it.newsImage)
                    .into(binding.imgNews)
            }
        }
    }


}