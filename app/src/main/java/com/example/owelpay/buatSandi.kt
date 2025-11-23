package com.example.owelpay

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class buatSandi : AppCompatActivity() {

    private lateinit var edtSandi: EditText
    private lateinit var edtKonfirmasi: EditText
    private lateinit var btnDaftar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_buat_sandi)

        val mainLayout = findViewById<ConstraintLayout>(R.id.sandi)
        ViewCompat.setOnApplyWindowInsetsListener(mainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            return@setOnApplyWindowInsetsListener insets
        }

        edtSandi = findViewById(R.id.input_number) // input sandi
        edtKonfirmasi = findViewById(R.id.column_number) // konfirmasi sandi
        btnDaftar = findViewById(R.id.btn_daftar)

        btnDaftar.setOnClickListener {
            val sandi = edtSandi.text.toString().trim()
            val konfirmasi = edtKonfirmasi.text.toString().trim()

            // cek dummy
            if (sandi.isEmpty() || konfirmasi.isEmpty()) {
                Toast.makeText(this, "Isi semua kolom!", Toast.LENGTH_SHORT).show()
            } else if (sandi != konfirmasi) {
                Toast.makeText(this, "Sandi tidak sama, coba lagi!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Sandi berhasil dibuat!", Toast.LENGTH_SHORT).show()
                // nanti bisa intent ke halaman login / home
            }
        }
    }
}
