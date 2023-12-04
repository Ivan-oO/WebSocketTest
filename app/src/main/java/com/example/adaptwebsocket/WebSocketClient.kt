package com.example.adaptwebsocket

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class WebSocketClient : WebSocketListener() {

    private var webSocket: WebSocket? = null

    fun start() {
        val client = OkHttpClient()
        val request = Request.Builder().url("wss://messenger-demo.adaptframework.solutions/broker").build()
        webSocket = client.newWebSocket(request, this)
        // client.dispatcher().executorService().shutdown()
    }

    fun sendMessage(message: String) {
        webSocket?.send(message)
    }

    override fun onOpen(webSocket: WebSocket, response: okhttp3.Response) {
        Log.d("WebSocket", "Connection opened")
        sendMessage("Hello, server!")  // Sending a test message upon opening the connection
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        Log.d("WebSocket", "Received message: $text")
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        // Binary message received
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        // Connection is closing
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        // Connection closed
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: okhttp3.Response?) {
        Log.e("WebSocket", "Error: " + t.message)
    }
}
