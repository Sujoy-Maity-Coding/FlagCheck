package com.sujoy.flagcheck.Screens

import android.media.MediaPlayer
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sujoy.flagcheck.R
import com.sujoy.flagcheck.ViewModel.QuizViewModel

@Composable
fun ResultScreen(viewModel: QuizViewModel = hiltViewModel(), onRestart: () -> Unit = {}) {
    val context = LocalContext.current
    val green by viewModel.greenScore
    val red by viewModel.redScore

    val bg = when {
        green > red -> R.drawable.green_love_bg
        red > green -> R.drawable.red_love_bg
        else -> R.drawable.yel_love_bg
    }

    val sound = when {
        green > red -> R.raw.green_song
        red > green -> R.raw.red_song
        else -> 0
    }

    val result = when {
        green > red -> "Green Flag 💚"
        red > green -> "Red Flag 🚩"
        else -> "Mixed Signals 🟡"
    }

    // 🔁 Remember MediaPlayer instance
    val mediaPlayer = remember { mutableStateOf<MediaPlayer?>(null) }

    // 🔊 Auto-play when screen opens
    LaunchedEffect(Unit) {
        if (sound != 0) {
            val player = MediaPlayer.create(context, sound)
            player.isLooping = false
            player.start()
            mediaPlayer.value = player
        }
    }

    // 🧹 Clean up when screen leaves
    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.value?.release()
            mediaPlayer.value = null
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = bg),
            contentDescription = "bg",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Card(
            modifier = Modifier.width(350.dp).height(400.dp),
            colors = CardDefaults.cardColors(Color(0x79FFFCFC))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Game Over!", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF9C0D02))
                Spacer(modifier = Modifier.height(12.dp))
                Text("Green: $green, Red: $red", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text(result, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {
                        // 🛑 Stop and release current sound
                        mediaPlayer.value?.stop()
                        mediaPlayer.value?.release()
                        mediaPlayer.value = null

                        // 🌀 Restart game
                        viewModel.resetGame()
                        onRestart()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Play Again", color = Color.White, fontSize = 20.sp, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
