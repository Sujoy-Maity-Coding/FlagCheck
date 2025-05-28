package com.sujoy.flagcheck.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sujoy.flagcheck.R

@Preview(showSystemUi = true)
@Composable
fun HomeScreen(onStart: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .height(230.dp)){
            AnimatedLottie(lottieRes = R.raw.love)
        }
        Text("A healthy relationship is built on trust, respect, and support — these are your green flags. But when you're made to feel small, unsafe, or unheard, those are red flags waving for your attention. Know the difference, and choose yourself first.",
            color = Color.Red, fontSize = 12.sp, fontStyle = FontStyle.Italic)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onStart,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            )
        ) {
            Text("Start Game", color = Color.White, fontSize = 20.sp, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold)
        }
    }
}

