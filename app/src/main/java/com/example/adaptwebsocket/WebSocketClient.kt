package com.example.adaptwebsocket

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class WebSocketClient(private val logUpdater: (String) -> Unit) : WebSocketListener() {
    private var webSocket: WebSocket? = null

    fun start() {
        val client = OkHttpClient()
        val request = Request.Builder().url("wss://messenger-demo.adaptframework.solutions/broker").build()
        webSocket = client.newWebSocket(request, this)
    }

    override fun onOpen(webSocket: WebSocket, response: okhttp3.Response) {
        logUpdater("Connection opened")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        logUpdater("Received message: $text")
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        logUpdater("Received bytes message")
        logUpdater("Bytes: $bytes")
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        logUpdater("Closing: $code / $reason")
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        logUpdater("Closed: $code / $reason")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: okhttp3.Response?) {
        logUpdater("Error: " + t.message)
    }
}
