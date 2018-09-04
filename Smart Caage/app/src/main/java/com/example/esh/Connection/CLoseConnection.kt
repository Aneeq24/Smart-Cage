package com.example.esh.Connection

import android.os.AsyncTask
import com.example.esh.Utilities.MyData
import java.io.IOException

/**
 * Created by ayoub on 11/26/17.
 */

class CLoseConnection : AsyncTask<Void , String , Void>(){

    override fun doInBackground(vararg params: Void?): Void? {
        try {

            MyData.socket?.close()
            System.out.println("connection closed")

        }catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }

}
