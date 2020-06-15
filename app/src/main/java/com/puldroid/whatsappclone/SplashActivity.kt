package com.puldroid.whatsappclone

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {
    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(auth.currentUser == null){
            startActivity(Intent(this,LoginActivity::class.java))
        }else{
            startActivity(Intent(this,MainActivity::class.java))
        }
        finish()

    }
}