package com.example.owelpay

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent


// Import SimpleTextWatcher dari file terpisah
import com.example.owelpay.SimpleTextWatcher

class kodeOtp : AppCompatActivity() {

    private lateinit var otp1: EditText
    private lateinit var otp2: EditText
    private lateinit var otp3: EditText
    private lateinit var otp4: EditText
    private lateinit var txtKirimUlang: TextView
    private lateinit var btnVerif: Button

    private val resendDelay: Long = 30000 // 30 detik
    private val dummyOtp = "1234" // OTP boongan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kode_otp)

        val mainLayout = findViewById<ConstraintLayout>(R.id.kodeOtp)
        ViewCompat.setOnApplyWindowInsetsListener(mainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // EditText OTP
        otp1 = findViewById(R.id.otp1)
        otp2 = findViewById(R.id.otp2)
        otp3 = findViewById(R.id.otp3)
        otp4 = findViewById(R.id.otp4)

        // tombol dan TextView resend
        txtKirimUlang = findViewById(R.id.txt_kirim_ulang)
        btnVerif = findViewById(R.id.btn_verif)

        enableResend()
        setupOtpInputs()

        // Resend OTP
        txtKirimUlang.setOnClickListener {
            disableResend()
            Toast.makeText(this, "Kode OTP terkirim ulang!", Toast.LENGTH_SHORT).show()
            startResendTimer()
        }

        // Verifikasi OTP
        btnVerif.setOnClickListener {
            val enteredOtp = otp1.text.toString() +
                    otp2.text.toString() +
                    otp3.text.toString() +
                    otp4.text.toString()

            if (enteredOtp.length < 4) {
                Toast.makeText(this, "Harap isi semua angka OTP!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (enteredOtp == dummyOtp) {
                Toast.makeText(this, "OTP berhasil diverifikasi!", Toast.LENGTH_SHORT).show()

                // Pindah ke halaman berikutnya
                val intent = Intent(this, buatSandi::class.java)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "OTP salah, coba lagi!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    // Timer resend OTP
    private fun startResendTimer() {
        object : CountDownTimer(resendDelay, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                txtKirimUlang.text = "Kirim lagi dalam ${millisUntilFinished / 1000}s"
            }

            override fun onFinish() {
                enableResend()
            }
        }.start()
    }

    private fun disableResend() {
        txtKirimUlang.isEnabled = false
        txtKirimUlang.setTextColor(resources.getColor(R.color.darker_gray, theme))
    }

    private fun enableResend() {
        txtKirimUlang.isEnabled = true
        txtKirimUlang.text = "Kirim"
        txtKirimUlang.setTextColor(resources.getColor(R.color.birulangit, theme))
    }

    // Otomatis pindah fokus antar kotak OTP
    private fun setupOtpInputs() {
        otp1.addTextChangedListener(SimpleTextWatcher { text ->
            if (text.length == 1) otp2.requestFocus()
        })
        otp2.addTextChangedListener(SimpleTextWatcher { text ->
            if (text.length == 1) otp3.requestFocus()
            else if (text.isEmpty()) otp1.requestFocus()
        })
        otp3.addTextChangedListener(SimpleTextWatcher { text ->
            if (text.length == 1) otp4.requestFocus()
            else if (text.isEmpty()) otp2.requestFocus()
        })
        otp4.addTextChangedListener(SimpleTextWatcher { text ->
            if (text.isEmpty()) otp3.requestFocus()
        })
    }
}
