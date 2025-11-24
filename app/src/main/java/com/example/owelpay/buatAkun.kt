package com.example.owelpay

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.enableEdgeToEdge
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent

class buatAkun : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_buat_akun)

        val mainLayout = findViewById<ConstraintLayout>(R.id.buatAkun)
        ViewCompat.setOnApplyWindowInsetsListener(mainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            return@setOnApplyWindowInsetsListener insets
        }

        val inputNama = findViewById<EditText>(R.id.input_number)
        val btnDaftar = findViewById<Button>(R.id.btn_daftar)

        btnDaftar.setOnClickListener {
            val nama = inputNama.text.toString().trim()

            if (nama.isEmpty()) {
                Toast.makeText(this, "Isi nama dulu!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Akun berhasil dibuat!", Toast.LENGTH_SHORT).show()

                // LANGSUNG PINDAH KE HOMEPAGE
                val intent = Intent(this@buatAkun, HomePage::class.java)
                startActivity(intent)

                // Biar halaman daftar nggak bisa balik pake back
                finish()
            }
        }

    }
}