package com.rapzinator.goodmusic.screen.reset

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.rapzinator.goodmusic.databinding.ActivityResetPasswordBinding
import com.rapzinator.goodmusic.screen.main.MainActivity

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResetPasswordBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.sendEmail.setOnClickListener {
            val email: String = binding.email.text.toString().trim()
            if (TextUtils.isEmpty(email) && !email.contains("@")) {
                binding.email.error = "Required Field.."
                return@setOnClickListener
            }

            auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                    Toast.makeText(this, "Successful Please check your emails", Toast.LENGTH_SHORT)
                } else {
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT)
                }
            }
        }
    }
}