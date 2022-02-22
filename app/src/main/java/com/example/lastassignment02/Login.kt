package com.example.lastassignment02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    var auth:FirebaseAuth? = null//จัดการบัญชีผู้ใช้งานบน Firebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth= FirebaseAuth.getInstance()//สร้าง Object เพื่อเรียกใช้งาน FirebaseAuth (ล็อคอินอีเมลจาก Firebase)
        //ตรวจสอบว่ามีการ Log in ค้างหรือไม่
        if (auth!!.currentUser!=null){
            //คำสั่ง Intent ไปยังฟอร์มอื่น
            val it= Intent(this,Member::class.java)
            startActivity(it)
            finish()
        }

        cmdLogin.setOnClickListener {
            val email = edtEmail.text.toString().trim()
            val pass = edtPassword.text.toString().trim()

            if (email.isEmpty()){
                Toast.makeText(this,"กรุณากรอก Email" , Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (pass.isEmpty()) {
                Toast.makeText(this, "กรุณากรอก Password", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            //คําสั่งบ Log in Email และ Password บน Firebase
            auth!!.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    if (pass.length < 8) {
                        edtPassword.error = "กรอกรหัสผ่านให้มากกว่า 8 ตัวอักษร"
                    } else {
                        Toast.makeText(
                            this,
                            "Login ล้มเหลวเนื่องจาก :" + task.exception!!.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }else{
                    Toast.makeText(this, "Login Success!", Toast.LENGTH_LONG).show()
                    val it = Intent(this, Member::class.java)
                    startActivity(it)
                    finish()
                }
            }
        }
        //ปุ่มลงทะเบียน
        cmdRegister.setOnClickListener {
            //Intent ไปยังฟอร์ม Register
            val it = Intent(this, Register::class.java)
            startActivity(it)
        }
        //ปุ่มกลับไปหน้าแรก
        cmdBack.setOnClickListener {
            //ถอยกลับไปฟอร์มก่อนหน้านี้
            onBackPressed()
        }

    }
}