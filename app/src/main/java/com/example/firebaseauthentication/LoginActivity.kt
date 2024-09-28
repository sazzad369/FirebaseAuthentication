package com.example.firebaseauthentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseauthentication.ViewModel.AuthViewModel
import com.example.firebaseauthentication.databinding.ActivityLoginBinding
import com.example.firebaseauthentication.databinding.ActivityRegistrationBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: AuthViewModel
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.loginBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()


            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this@LoginActivity, "Please Fill All Fields",
                    Toast.LENGTH_SHORT).show()

            } else{
                viewModel.signIn(email,password).observe(this,{result->
                    Toast.makeText(this@LoginActivity,result, Toast.LENGTH_SHORT).show()

                    if (result.equals("signIn Successful")){

                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    }
                })
            }
        }
        binding.registerTxt.setOnClickListener{
            startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java))
        }
    }
}