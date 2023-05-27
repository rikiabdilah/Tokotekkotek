package com.example.tokotekkotek.view.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tokotekkotek.R
import com.example.tokotekkotek.data.UserPreferences
import com.example.tokotekkotek.databinding.FragmentLoginBinding
import com.example.tokotekkotek.model.ResponseDataUserItem
import com.example.tokotekkotek.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding
    private val userViewModel : UserViewModel by viewModels()
    private lateinit var userPreferences: UserPreferences

    //get id
    private var idUser : Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userPreferences = UserPreferences(requireContext().applicationContext)

        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registFragment)
        }
    }

    private fun login() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty()){
            userViewModel.getDataAllUsers()

            userViewModel.users.observe(viewLifecycleOwner){
                if(it != null){
                    val checkLoginUser = checkLogin(email, password, it)
                    if(checkLoginUser){
                        Toast.makeText(context, "Login success", Toast.LENGTH_SHORT).show()

                        if(idUser != 0){
                            lifecycleScope.launch {
                                userPreferences.saveUser(idUser)
                            }
                            Log.i("USER_ID_BEFORE", idUser.toString())

                            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        }
                    }else{
                        Toast.makeText(context, "Login fail $email & $password", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(context, "Login fail", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(context, "form cannot be empty!", Toast.LENGTH_SHORT).show()
        }
    }
    private fun checkLogin(email : String, password : String, listUsers : List<ResponseDataUserItem> ) : Boolean{
        for (user in listUsers){
            if(user.email == email && user.password == password){
                idUser = user.idUsers.toInt()
                return true
            }
        }
        return false
    }

}