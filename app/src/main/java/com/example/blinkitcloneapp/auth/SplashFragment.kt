package com.example.blinkitcloneapp.auth

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.blinkitcloneapp.R
import com.example.blinkitcloneapp.activity.UsersMainActivity
import com.example.blinkitcloneapp.databinding.FragmentSplashBinding
import com.example.blinkitcloneapp.viewmodels.AuthViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {



    private lateinit var binding: FragmentSplashBinding

    private val viewModel : AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)

        // Use Handler from android.os package
        android.os.Handler(Looper.getMainLooper()).postDelayed({


            lifecycleScope.launch {

                viewModel.isCurrentUser.collect{
                    if(it) {
                        startActivity(Intent(requireActivity(), UsersMainActivity::class.java))
                        requireActivity().finish()
                    }
                    else
                    {
                        findNavController().navigate(R.id.action_splashFragment_to_signInFragment)

                    }
                }
            }

        }, 3000)  // Delay of 3 seconds

        return binding.root  // Return the root view for this fragment



    }


}