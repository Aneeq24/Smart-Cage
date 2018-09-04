package com.example.esh.Interface

interface MainView {
    fun openConnection()
    fun closeConnection()
    fun sendMessage(msg : String)
    fun receiveMessage()
}