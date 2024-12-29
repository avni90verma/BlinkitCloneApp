package com.example.blinkitcloneapp.auth

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.blinkitcloneapp.R
import com.example.blinkitcloneapp.Utils
import com.example.blinkitcloneapp.databinding.FragmentSignInBinding


class SignInFragment : Fragment() {


    private lateinit var binding: FragmentSignInBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        getUserNumber()
        onContinueButtonClicked()
        return binding.root
    }


    private fun onContinueButtonClicked(){
        binding.btnContinue.setOnClickListener{
            val number = binding.etUserNumber.text.toString()

            if(number.isEmpty()||number.length!=10 ){
                 Utils.showToast(requireContext(),"Please Enter a valid phone number")
            }
            else{
                val bundle = Bundle()
                bundle.putString("number",number)
                findNavController().navigate(R.id.action_signInFragment_to_OTPFragment,bundle)

            }

        }
    }
    private fun getUserNumber() {
        binding.etUserNumber.addTextChangedListener (
            object : TextWatcher  {
                override fun beforeTextChanged(number: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(number: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    val length = number?.length

                    if(length==10){
                        binding.btnContinue.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.green))
                    }
                    else{
                        binding.btnContinue.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.grayish_blue))
                    }
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            }
        )
    }




}