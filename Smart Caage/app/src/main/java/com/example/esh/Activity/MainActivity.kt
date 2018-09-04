package com.example.esh.Activity

import android.os.*
import android.support.v7.app.AppCompatActivity
import android.support.annotation.RequiresApi
import android.widget.Toast
import butterknife.ButterKnife
import com.example.esh.Interface.MainView
import com.example.esh.Utilities.Pref
import com.example.esh.R
import com.example.esh.Utilities.MyData
import com.example.esh.Utilities.App
import com.example.esh.Connection.CLoseConnection
import com.example.esh.Connection.OpenConnection
import com.example.esh.Connection.SendMessages
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    private var input: BufferedReader? = null

    @Inject
    lateinit var pref: Pref

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (this.application as App).getAppComponent()!!.inject(this)

        ButterKnife.bind(this)

        MyData.mainActivity = this

        bulb.setOnClickListener {
            val m: String = if (bulb.isChecked)
                "a"
            else
                "A"
            sendMessage("u")
            sendMessage(m)
        }

        fan.setOnClickListener {
            val m: String = if (fan.isChecked)
                "z"
            else
                "Z"
            sendMessage("u")
            sendMessage(m)
        }

        feed.setOnClickListener {
            val m: String = if (feed.isChecked)
                "c"
            else
                "C"
            sendMessage("u")
            sendMessage(m)
        }

        forward.setOnClickListener {
            sendMessage("f")
            sendMessage("u")
        }

        reverse.setOnClickListener {
            sendMessage("r")
            sendMessage("u")
        }

        refresh_connection.setOnClickListener {
            openConnection()
        }

    }


    override fun sendMessage(msg: String) {
        val sendMessages = SendMessages(msg)
        sendMessages.execute()
    }

    override fun openConnection() {
        val openConnection = OpenConnection(pref.ipAddress!!, pref.portNumber)
        openConnection.execute()
        Toast.makeText(applicationContext,"Connected",Toast.LENGTH_LONG).show()
    }

    override fun receiveMessage() {
        doAsync {

            input = BufferedReader(InputStreamReader(MyData.socket.getInputStream()))
            var msgText: String
            MyData.THREAD_RUNNING = true

            while (true) {
                Thread.sleep(300)
                msgText = input?.readLine().toString()

                uiThread {
                    temp.text = msgText
                }
            }
        }
    }

    override fun closeConnection() {
        val closeConnection = CLoseConnection()
        closeConnection.execute()
    }


    override fun onResume() {
        super.onResume()
        openConnection()
    }

    override fun onPause() {
        super.onPause()
        closeConnection()
    }

}
