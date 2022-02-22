package com.example.lastassignment02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    var auth:FirebaseAuth?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance() //สร้าง Object เพื่อเรียกใช้งาน FirebaseAuth (ล็อคอินิีเมล์จาก Firebase)
        //ตรวจสอบว่ามีการ Log in ค้างหรือไม่
        if(auth!!.currentUser != null) {
            //คำสั่ง Intent ไปยังฟอร์มอื่น
            val it = Intent(this, Member::class.java)
            startActivity(it)
            finish()
        }
        //ปุ่ม Register
        cmdRegister.setOnClickListener {
            //รับ email และ password จาก editText
            val email = edtEmail.text.toString().trim()
            val pass = edtPassword.text.toString().trim()

            //ตรวจสอบค่าว่าใน editText
            if (email.isEmpty()) {
                //แสดงข้อความแบบ Toast ล่างจอ
                Toast.makeText(this, "กรุณากรอก Email", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (pass.isEmpty()) {
                //แสดงข้อความแบบ Toast ล่างจอ
                Toast.makeText(this, "กรุณากรอก Password", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            //คำสั่งบบันทึก Email และ Password บน Firebase
            auth!!.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    if (pass.length < 8) {
                        edtPassword.error = "กรอกรหัสผ่านให้มากกว่า 8 ตัวอักษร"
                    } else {
                        Toast.makeText(
                            this,
                            "Login ล้มเหลว เนื่องจาก:" + task.exception!!.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this,
                        "Login Success!" + task.exception!!.message,
                        Toast.LENGTH_LONG
                    ).show()
                    val it = Intent(this, Member::class.java)
                    startActivity(it)
                    finish()
                }
            }
        }

        cmdLogin.setOnClickListener {
            val it = Intent(this, cmdLogin::class.java)
            startActivity(it)
        }

    }
}