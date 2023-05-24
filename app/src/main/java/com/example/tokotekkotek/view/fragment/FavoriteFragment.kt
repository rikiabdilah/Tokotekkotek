package com.example.tokotekkotek.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.tokotekkotek.R
import com.example.tokotekkotek.databinding.FragmentFavoriteBinding
import com.example.tokotekkotek.view.adapter.FavoriteAdapter
import com.example.tokotekkotek.viewmodel.FavoriteViewModel

class FavoriteFragment : Fragment() {

    private var _binding : FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel : FavoriteViewModel by viewModels()
    private lateinit var mainAdapter : FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        initList()
//        viewModel.session()
//        viewModel.user.observe(viewLifecycleOwner) {
//            if (it != null) {
//                viewModel.getAllFavorites(it.uid)
//            }
//        }
//    }

    private fun initList() {
        TODO("Not yet implemented")
    }
}