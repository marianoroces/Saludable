package com.marianoroces.norris.tpfinal.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.marianoroces.norris.tpfinal.databinding.FragmentLoginBinding
import com.marianoroces.norris.tpfinal.view.MainActivity
import com.marianoroces.norris.tpfinal.viewmodel.UserViewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userVM: UserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.flLogin.setOnClickListener {
            if (validateInputs()) {
                if (userVM.getUser(
                        it.context,
                        binding.flUser.text.toString(),
                        binding.flPassword.text.toString()
                    )
                ) {
                    val intent = Intent(it.context, MainActivity::class.java)
                    intent.putExtra("user", userVM.user.value)
                    startActivity(intent)
                } else {
                    Toast.makeText(it.context, "Usuario o contraseña erroneos", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        var result = true

        if (binding.flUser.text.toString() == "") {
            binding.flUserLayout.error = "Completar"
            result = false
        } else
            binding.flUserLayout.error = ""

        if (binding.flPassword.text.toString() == "") {
            binding.flPasswordLayout.error = "Completar"
            result = false
        } else
            binding.flPasswordLayout.error = ""

        return result
    }
}