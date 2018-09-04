package com.example.esh.Utilities

import android.annotation.SuppressLint
import com.example.esh.Activity.MainActivity
import java.net.Socket

object MyData {
    lateinit var socket: Socket
    @SuppressLint("StaticFieldLeak")
    lateinit var mainActivity : MainActivity
    var THREAD_RUNNING = false
}
