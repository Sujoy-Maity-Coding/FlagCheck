package com.sujoy.flagcheck.Screens

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.sujoy.flagcheck.R

fun playSound(context: Context, soundResId: Int, onComplete: () -> Unit) {
    val mediaPlayer = MediaPlayer.create(context, soundResId)
    mediaPlayer.setOnCompletionListener {
        it.release()
        onComplete()
    }
    mediaPlayer.start()
}

