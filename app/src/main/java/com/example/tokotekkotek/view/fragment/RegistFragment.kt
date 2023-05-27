package com.example.tokotekkotek.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tokotekkotek.R
import com.example.tokotekkotek.databinding.FragmentRegistBinding
import com.example.tokotekkotek.model.DataUserRegist
import com.example.tokotekkotek.model.ResponseDataUserItem
import com.example.tokotekkotek.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistFragment : Fragment() {

    private lateinit var binding : FragmentRegistBinding
    private val userViewModel : UserViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            signUp()
        }

        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_registFragment_to_loginFragment)
        }
    }

    private fun signUp() {
        val name = binding.etNameReg.text.toString()
        val email = binding.etEmailReg.text.toString()
        val password = binding.etPassReg.text.toString()

        if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {

            //call viewmodel user
            userViewModel.getDataAllUsers()

            //observe user livedata
            userViewModel.users.observe(viewLifecycleOwner){
                val checkEmailPasswordNotExits = checkEmailPasswordIsNotExist(email,password, it)

                if(checkEmailPasswordNotExits){

                    userViewModel.registDataUser(
                        DataUserRegist(email,"image user",name,password)
                    )

                    userViewModel.usersRegist.observe(viewLifecycleOwner){responseUser->
                        if(responseUser != null ){
                            Toast.makeText(context, "Registration success", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_registFragment_to_loginFragment)
                        }else{
                            Toast.makeText(context, "Registration failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(context, "email or password is already exits", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(context, "form cannot be empty!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkEmailPasswordIsNotExist(email: String, password: String, listUser: List<ResponseDataUserItem>): Boolean {
        for(user in listUser){
            if(user.email == email || user.password == password){
                return false
            }
        }
        return true
    }

}