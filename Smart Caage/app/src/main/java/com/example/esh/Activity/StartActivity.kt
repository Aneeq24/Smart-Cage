package com.example.esh.Activity

import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast

import com.example.esh.Utilities.Pref
import com.example.esh.R
import com.example.esh.Utilities.Util
import com.example.esh.Utilities.App

import javax.inject.Inject

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

class StartActivity : AppCompatActivity() {

    @BindView(R.id.ip_address)
    lateinit var ipAddress: EditText

    @BindView(R.id.port_number)
    lateinit var portNumber: EditText

    @Inject
    lateinit var pref: Pref

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        (this.application as App).getAppComponent()!!.inject(this)

        ButterKnife.bind(this)


        if (Util.isValidIp(pref.ipAddress!!) && pref.portNumber != 0) {
            startMainActivity()
        }
    }

    @OnClick(R.id.login_next)
    fun next() {

        if (Util.isValidIp(ipAddress.text.toString())) {
            pref.ipAddress = ipAddress.text.toString()
        } else {
            Toast.makeText(this, "Enter valid IP address", Toast.LENGTH_SHORT).show()
            return
        }

        if (portNumber.text.toString() != "") {
            pref.portNumber = Integer.parseInt(portNumber.text.toString())
        } else {
            Toast.makeText(this, "Enter port number", Toast.LENGTH_SHORT).show()
            return
        }
        startMainActivity()
    }

    fun startMainActivity() {
        val intent = Intent(this@StartActivity, MainActivity::class.java)
        intent.flags = (Intent.FLAG_ACTIVITY_CLEAR_TASK
                or Intent.FLAG_ACTIVITY_CLEAR_TOP
                or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

}
