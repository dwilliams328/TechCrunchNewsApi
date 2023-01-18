package com.example.techcrunchnewsapi.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.techcrunchnewsapi.R
import com.example.techcrunchnewsapi.databinding.FragmentLoginBinding
import com.example.techcrunchnewsapi.ui.register.SigninResult
import com.example.techcrunchnewsapi.ui.stateholders.UserViewModel
import com.example.techcrunchnewsapi.ui.stateholders.UserViewModelFactory
import com.google.firebase.messaging.FirebaseMessaging


class LoginFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userViewModel = ViewModelProvider(this, UserViewModelFactory())[UserViewModel::class.java]
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val usernameEditText = binding.username
        val passwordEditText = binding.password
        val loginButton = binding.login
        val loadingProgressBar = binding.loading

        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            Log.d("abc", it.result)
        }

        userViewModel.loginFormState.observe(viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                loginButton.isEnabled = loginFormState.isDataValid
                loginFormState.usernameError?.let {
                    usernameEditText.error = getString(it)
                }
                loginFormState.passwordError?.let {
                    passwordEditText.error = getString(it)
                }
            })

        userViewModel.loginResult.observe(viewLifecycleOwner,
            Observer { signinResult ->
                if (signinResult.success != null) {
                    loadingProgressBar.visibility = View.GONE
                    updateUiWithUser(signinResult)
                }

            })

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                userViewModel.registerDataChanged(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }
        usernameEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                userViewModel.login(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                ){
                    // ignore
                }
            }
            false
        }

        loginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            userViewModel.login(
                usernameEditText.text.toString(),
                passwordEditText.text.toString()
            ){
                if (it.success != null) {
                    loadingProgressBar.visibility = View.GONE
                    findNavController().navigate(R.id.action_LoginFragment_to_AuthenticatedUserFragment)
                } else {
                    loadingProgressBar.visibility = View.GONE
                    Toast.makeText(context, "error signing in", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun updateUiWithUser(model: SigninResult) {
        val welcome = getString(R.string.welcome) + " " + model.success?.email
        // TODO : initiate successful logged in experience
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
    }

    private fun showToastOnError() {

    }
}