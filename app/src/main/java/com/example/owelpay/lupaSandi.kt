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


class lupaSandi : AppCompatActivity() {
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

        val btn_daftar = findViewById<Button>(R.id.btn_daftar)


        btn_daftar.setOnClickListener {
            val inputNumber = findViewById<EditText>(R.id.input_number)
            val phone = inputNumber.text.toString().trim()

            // VALIDASI: kosong
            if (phone.isEmpty()) {
                inputNumber.error = "Nomor HP tidak boleh kosong"
                return@setOnClickListener
            }

            // VALIDASI: nomor hp
            if (phone.length < 10) {
                inputNumber.error = "Nomor HP tidak valid"
                return@setOnClickListener
            }

            // VALIDASI: harus angka semua
            if (!phone.matches(Regex("^[0-9]+$"))) {
                inputNumber.error = "Nomor HP hanya boleh angka"
                return@setOnClickListener
            }

            // Kalau lolos validasi → lanjut ke OTP
            val intent = Intent(this, kodeOtp::class.java)
            intent.putExtra("nomor_hp", phone)
            startActivity(intent)
        }

    }
}