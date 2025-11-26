package com.example.owelpay

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText


class LupaSandi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lupa_sandi)

        val mainLayout = findViewById<ConstraintLayout>(R.id.lupa_sandi)
        ViewCompat.setOnApplyWindowInsetsListener(mainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn_masuk = findViewById<Button>(R.id.btn_masuk)


        btn_masuk.setOnClickListener {
            val inputNumber = findViewById<EditText>(R.id.input_email_sandi)
            val userInput = inputNumber.text.toString().trim()

            // VALIDASI: kosong
            if (userInput.isEmpty()) {
                inputNumber.error = "Masukkan email yang digunakan akun anda"
                return@setOnClickListener
            }

            val isEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(userInput).matches()
            val isPhone = userInput.matches(Regex("^[0-9]+$"))

            if (!isEmail && !isPhone) {
                inputNumber.error = "Masukkan format Email atau Nomor HP yang valid"
                return@setOnClickListener
            }

            if (isPhone && userInput.length < 10){
                inputNumber.error = "Nomor HP terlalu pendek"
                return@setOnClickListener
            }

            // Kalau lolos validasi → lanjut ke OTP
            val intent = Intent(this, kodeOtp::class.java)
            startActivity(intent)
        }

    }
}