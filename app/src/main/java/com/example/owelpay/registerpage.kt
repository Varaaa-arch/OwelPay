package com.example.owelpay

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.constraintlayout.widget.ConstraintLayout
import android.widget.TextView
import android.widget.Toast
import android.content.Intent
import android.widget.Button



class registerpage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_registerpage)

        val mainLayout = findViewById<ConstraintLayout>(R.id.register)
        ViewCompat.setOnApplyWindowInsetsListener(mainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }   

        // buat btnHp
        val btnNoHp = findViewById<Button>(R.id.btnNoHp)

        btnNoHp.setOnClickListener {
            val intent = Intent(this, registerPage2::class.java)
            startActivity(intent)
        }

        // buat btnAkunGoogle
        val btnAkunGoogle = findViewById<Button>(R.id.btngoogle)
        btnAkunGoogle.setOnClickListener {
            val intent = Intent(this, akunGoogle::class.java)
            startActivity(intent)
        }

        // Buat klik text login
        val loginButton = findViewById<TextView>(R.id.login_button)
//        loginButton.setOnClickListener {
//            val intent = Intent(this, registerPage2::class.java)
//            startActivity(intent)
//        }
        loginButton.setOnClickListener {
            Toast.makeText(this, "Login Di klik", Toast.LENGTH_SHORT).show()
        }
    }
}
