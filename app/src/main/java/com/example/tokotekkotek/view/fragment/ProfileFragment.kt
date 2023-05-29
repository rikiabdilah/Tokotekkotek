package com.example.tokotekkotek.view.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.tokotekkotek.R
import com.example.tokotekkotek.data.UserPreferences
import com.example.tokotekkotek.databinding.FragmentProfileBinding
import com.example.tokotekkotek.model.ResponseDataUserItem
import com.example.tokotekkotek.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var userPreferences: UserPreferences
    private val userViewModel : UserViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userPreferences = UserPreferences(requireContext().applicationContext)
        checkActiveUser()

        binding.logout.setOnClickListener {
            signOut()
        }

    }

    private fun signOut() {
        val dialogConfirmSignOut = AlertDialog.Builder(context)

        dialogConfirmSignOut.apply {
            setTitle("Sign Out Confirmation")
            setMessage("Do you want to sign out this account?")
            setNegativeButton("Cancel"){ option, _ ->
                option.dismiss()
            }

            setPositiveButton("Sign Out"){ _, _ ->
                lifecycleScope.launch {
                    userPreferences.clear()
                }
            }
        }

        dialogConfirmSignOut.show()
    }

    private fun checkActiveUser() {
        userPreferences.isLoggin().asLiveData().observe(viewLifecycleOwner){
            if(it != null){
                if (it){
                    binding.btnLogin.visibility = View.GONE
                    binding.layoutButton.visibility = View.VISIBLE
                    setDataProfile()
                }else{
                    binding.btnLogin.visibility = View.VISIBLE
                    binding.layoutButton.visibility = View.GONE
                }
            }
        }
    }

    private fun setDataProfile() {
        userPreferences.idUser.asLiveData().observe(viewLifecycleOwner){
            if(it != 0){
                userViewModel.getDataDetailUser(it)

                userViewModel.detailUser.observe(viewLifecycleOwner){dataUser->
                    if(dataUser != null){
                        binding.user = dataUser
                    }else{
                        binding.user = ResponseDataUserItem(
                            "","empty", "","", "empty",""
                        )
                    }
                }
            }
        }
    }


}