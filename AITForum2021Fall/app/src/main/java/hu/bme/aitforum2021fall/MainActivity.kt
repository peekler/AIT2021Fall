package hu.bme.aitforum2021fall

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import hu.bme.aitforum2021fall.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            registerUser()
        }

        binding.btnLogin.setOnClickListener {
            loginUser()
        }
    }

    private fun registerUser() {
        if (isFormValid()) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                binding.etEmail.text.toString(), binding.etPassword.text.toString()
            ).addOnSuccessListener {
                Toast.makeText(this, "User CREATED", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Error: ${it.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun loginUser(){
        if (isFormValid()){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                binding.etEmail.text.toString(), binding.etPassword.text.toString()
            ).addOnSuccessListener {
                startActivity(Intent(this, ScrollingActivity::class.java))
            }.addOnFailureListener {
                Toast.makeText(this,
                    "Login failed: ${it.message}", Toast.LENGTH_LONG).show()
            }
        }
    }


    fun isFormValid(): Boolean {
        return when {
            binding.etEmail.text.isEmpty() -> {
                binding.etEmail.error = "This field can not be empty"
                false
            }
            binding.etPassword.text.isEmpty() -> {
                binding.etPassword.error = "The password can not be empty"
                false
            }
            else -> true
        }
    }

}