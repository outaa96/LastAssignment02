package com.example.lastassignment02

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_member.*

class Member : AppCompatActivity() {
    var auth : FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member)
        auth = FirebaseAuth.getInstance()

        val  userData = auth!!.currentUser
        tvOutput.text = userData?.uid.toString()+ "" + userData!!.email

        //ปุ่ม Logout
        cmdLogout.setOnClickListener {
            auth!!.signOut() //คำสั่ง Logout Email
            Toast.makeText(this, "Logout Complete", Toast.LENGTH_LONG).show()

            val it = Intent(this, MainActivity::class.java)
            startActivity(it)
            finish()
        }
    }
}