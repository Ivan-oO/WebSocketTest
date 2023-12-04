package com.example.adaptwebsocket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.adaptwebsocket.ui.theme.AdaptWebSocketTheme

class MainActivity : ComponentActivity() {
    private lateinit var webSocketClient: WebSocketClient
    val webSocketLogs = mutableStateListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdaptWebSocketTheme {
                WebSocketLogs(webSocketLogs)
            }
        }

        webSocketClient = WebSocketClient { log ->
            runOnUiThread {
                webSocketLogs.add(log)
            }
        }
        webSocketClient.start()
    }
}

@Composable
fun WebSocketLogs(logs: List<String>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        logs.forEach { log ->
            Text(text = log)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WebSocketLogsPreview() {
    AdaptWebSocketTheme {
        WebSocketLogs(listOf("Log 1", "Log 2"))
    }
}
