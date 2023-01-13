package com.example.techcrunchnewsapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.techcrunchnewsapi.R
import com.example.techcrunchnewsapi.databinding.FragmentSignedInUserBinding
import com.example.techcrunchnewsapi.ui.register.UserViewModelFactory
import com.example.techcrunchnewsapi.ui.register.UserViewModel
import com.google.firebase.auth.FirebaseAuth

class SignedInUserFragment : Fragment() {
    private lateinit var viewModel: UserViewModel

    private lateinit var binding: FragmentSignedInUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignedInUserBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this, UserViewModelFactory())[UserViewModel::class.java]

        binding.tvWelcome.text = FirebaseAuth.getInstance().currentUser?.email

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogout.setOnClickListener {
            viewModel.logout()
            findNavController().navigate(R.id.action_SignedInUserFragment_to_FirstFragment)
        }
    }
}