package com.example.lastassignment02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var auth:FirebaseAuth?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth= FirebaseAuth.getInstance()//สร้าง Object เพื่อเรียกใช้งาน FirebaseAuth (ล็อคอินอีเมลจาก Firebase)
        //ตรวจสอบว่ามีการ Log in ค้างหรือไม่
        if (auth!!.currentUser!=null){
            //คำสั่ง Intent ไปยังฟอร์มอื่น
            val it=Intent(this,Member::class.java)
            startActivity(it)
            finish()
        }

        //ปุ่ม Login
        cmdLoginWithEmail.setOnClickListener {
            //คำสั่ง Intent ไปยังฟอร์ม Login
            val it = Intent(this, Login::class.java)
            startActivity(it)
        }
    }
}