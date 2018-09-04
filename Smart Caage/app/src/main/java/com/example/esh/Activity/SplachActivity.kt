package com.example.esh.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.esh.R

class SplachActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach)

        val background = object : Thread() {
            override fun run() {
                try {
                    // Thread will sleep for 2 seconds
                    Thread.sleep((2 * 1000).toLong())

                    // After 5 seconds redirect to another intent
                    val i = Intent(baseContext, StartActivity::class.java)
                    startActivity(i)
                    //Remove activity
                    finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        // start thread
        background.start()
    }

}
