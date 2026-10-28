package com.krish.videoeditor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { EditorScreen() }
    }
}

private val Bg = Color(0xFF0D0F14)
private val Panel = Color(0xFF181B22)
private val Accent = Color(0xFF7C5CFF)

@Composable
fun EditorScreen() {
    var playing by remember { mutableStateOf(false) }
    val tracks = listOf(
        Triple("Video", Color(0xFF3B82F6), 0.8f),
        Triple("Text", Color(0xFFF59E0B), 0.4f),
        Triple("Audio", Color(0xFF10B981), 0.9f)
    )
    val tools = listOf("Trim", "Text", "Effects", "Audio", "Speed")

    Column(
        Modifier
            .fillMaxSize()
            .background(Bg)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Row(
            Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Video Editor", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = Accent)) {
                Text("Export")
            }
        }

        Box(
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .clip(RoundedCornerShape(12.dp))
                .background(Panel),
            contentAlignment = Alignment.Center
        ) {
            Text("Preview", color = Color.Gray)
        }

        Row(
            Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("00:00 / 00:00", color = Color.Gray, fontSize = 14.sp)
            Button(onClick = { playing = !playing }, colors = ButtonDefaults.buttonColors(containerColor = Accent)) {
                Text(if (playing) "Pause" else "Play")
            }
        }

        Column(
            Modifier.weight(1f).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Timeline", color = Color.White, fontWeight = FontWeight.SemiBold)
            tracks.forEach { (name, color, fraction) ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(name, color = Color.Gray, fontSize = 12.sp, modifier = Modifier.width(52.dp))
                    Box(
                        Modifier
                            .weight(1f)
                            .height(36.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Panel)
                    ) {
                        Box(
                            Modifier
                                .fillMaxWidth(fraction)
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(8.dp))
                                .background(color)
                        )
                    }
                }
            }
        }

        Row(
            Modifier.fillMaxWidth().background(Panel).padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            tools.forEach { t ->
                TextButton(onClick = {}) { Text(t, color = Color.White, fontSize = 13.sp) }
            }
        }
    }
}
