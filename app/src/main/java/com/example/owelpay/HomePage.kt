package com.example.owelpay

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)

        val mainLayout = findViewById<ConstraintLayout>(R.id.home_page)

        ViewCompat.setOnApplyWindowInsetsListener(mainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        // data nama dari intent
        val namaUser = intent.getStringExtra("nama_user")

        val txtNama = findViewById<TextView>(R.id.txtNama)

        if (namaUser != null) {
            txtNama.text = "$namaUser!"
        } else {
            txtNama.text = "Error!"
        }
    }
}
