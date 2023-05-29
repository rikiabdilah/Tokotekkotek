package com.example.tokotekkotek.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tokotekkotek.R
import com.example.tokotekkotek.data.UserPreferences
import com.example.tokotekkotek.databinding.FragmentHistoryTransactionBinding
import com.example.tokotekkotek.view.adapter.HistoryTransactionAdapter
import com.example.tokotekkotek.viewmodel.HistoryTransactionViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class HistoryTransactionFragment : Fragment() {


    private lateinit var binding: FragmentHistoryTransactionBinding
    private lateinit var userPreferences: UserPreferences
    private val historyTransactionViewModel : HistoryTransactionViewModel by viewModels()
    private lateinit var historyTransactionAdapter: HistoryTransactionAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryTransactionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userPreferences = UserPreferences(requireContext().applicationContext)

        historyTransactionAdapter = HistoryTransactionAdapter(ArrayList())

        val userActive = checkActiveUser()

        setHistoryTransaction()
    }

    private fun setHistoryTransaction() {

        binding.rvHistoryTransaction.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = historyTransactionAdapter
        }
        userPreferences.idUser.asLiveData().observe(viewLifecycleOwner){
            if(it != 0){
                historyTransactionViewModel.getDataAllHistoryTransaction(it)

                historyTransactionViewModel.allHistoryTransaction.observe(viewLifecycleOwner){dataHistoryTrans->
                    if(dataHistoryTrans != null){
                        historyTransactionAdapter.setDataListNewProduct(dataHistoryTrans)
                    }
                }
            }
        }
    }

    private fun checkActiveUser() {
        userPreferences.isLoggin().asLiveData().observe(viewLifecycleOwner){
            if(it != null){
                if (it){
                    binding.btnLogin.visibility = View.GONE
                    binding.rvHistoryTransaction.visibility = View.VISIBLE
                }else{
                    binding.btnLogin.visibility = View.VISIBLE
                    binding.rvHistoryTransaction.visibility = View.GONE
                }
            }
        }
    }

    fun convertToRupiah(number: Int): String{

        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number).toString()
    }

}