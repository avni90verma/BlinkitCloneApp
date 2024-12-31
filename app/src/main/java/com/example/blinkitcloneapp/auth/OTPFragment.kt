package com.example.blinkitcloneapp.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.blinkitcloneapp.R
import com.example.blinkitcloneapp.Utils
import com.example.blinkitcloneapp.activity.UsersMainActivity
import com.example.blinkitcloneapp.databinding.FragmentOTPBinding
import com.example.blinkitcloneapp.models.Users
import com.example.blinkitcloneapp.viewmodels.AuthViewModel
import kotlinx.coroutines.launch


class OTPFragment : Fragment() {


    private val viewModel : AuthViewModel by viewModels()

    private lateinit var binding: FragmentOTPBinding
    private lateinit var userNumber: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOTPBinding.inflate(inflater, container, false)

        getUserNumber()
        customizingEnteringOTP()
        sendOTP()
        onLoginButtonClicked()
        onBackButtonClicked()
        return binding.root
    }

    private fun getUserNumber(){
        val bundle = arguments
        userNumber = bundle?.getString("number").toString()

        binding.tvUserNumber.text = userNumber


    }

    private fun customizingEnteringOTP(){
        val editTexts = arrayOf(binding.etOtp1,binding.etOtp2,binding.etOtp3,binding.etOtp4,binding.etOtp5,binding.etOtp6)

        for(i in editTexts.indices){
            editTexts[i].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    if(s?.length == 1){
                        if(i<editTexts.size-1)
                        {
                            editTexts[i+1].requestFocus()
                        }
                    }
                    else if (s?.length==0){
                        if(i>0){
                            editTexts[i-1].requestFocus()
                        }
                    }

                }
            })
        }
    }
    private fun sendOTP(){
        Utils.showDialog(requireContext(),"Sending OTP...")

        viewModel.apply {
            sendOTP(userNumber,requireActivity())
            lifecycleScope.launch {
                otpSent.collect{
                    if(it){
                        Utils.hideDialog()
                        Utils.showToast(requireContext(),"Otp sent...")
                    }
                }
            }
        }


    }
    private fun onLoginButtonClicked(){
        binding.btnlogin.setOnClickListener{
            Utils.showDialog(requireContext(),"Signing you...")
            val editTexts = arrayOf(binding.etOtp1,binding.etOtp2,binding.etOtp3,binding.etOtp4,binding.etOtp5,binding.etOtp6)

            val otp = editTexts.joinToString("") {it.text.toString() }
            if(otp.length<editTexts.size){
                Utils.showToast(requireContext(),"Please enter right otp")
            }
            else{

                editTexts.forEach {
                    it.text?.clear();it.clearFocus()
                }
                verifyOtp(otp)

            }
        }
    }

    private fun verifyOtp(otp:String) {
        val user = Users(uid = Utils.getCurrentUserId(),userPhoneNumber = userNumber, userAddress = null)
      viewModel.signInWithPhoneAuthCredential(otp,userNumber,user)


        lifecycleScope.launch {
            viewModel.isSignedInSuccessfully.collect{
                if(it){
                    Utils.hideDialog()
                    Utils.showToast(requireContext(),"Logged in...")
                    startActivity(Intent(requireContext(),UsersMainActivity::class.java))
                    requireActivity().finish()
                }
            }
        }
    }

    private fun onBackButtonClicked(){
        binding.tbOtpFragment.setNavigationOnClickListener{
            findNavController().navigate(R.id.action_OTPFragment_to_signInFragment)

        }
    }



}