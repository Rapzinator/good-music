package com.rapzinator.goodmusic.screen.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.rapzinator.goodmusic.databinding.ActivityRegistrationBinding
import com.rapzinator.goodmusic.screen.main.MainActivity
import com.rapzinator.goodmusic.screen.login.LoginActivity

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnReg.setOnClickListener {
            val email: String = binding.emailReg.text.toString().trim()
            val password: String = binding.passwordReg.toString().trim()

            if (TextUtils.isEmpty(email)) {
                binding.emailReg.error = "Required Field.."
            }

            if (TextUtils.isEmpty(password)) {
                binding.passwordReg.error = "Required Field.."
                return@setOnClickListener
            }
            showProgressBar()

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                    Toast.makeText(this, "Successful", Toast.LENGTH_SHORT)
                } else {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT)
                }
                hideProgressBar()
            }
        }

        binding.signInTxt.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }


}