package com.sujoy.flagcheck.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sujoy.flagcheck.R
import com.sujoy.flagcheck.ViewModel.QuizViewModel
import kotlinx.coroutines.delay

@Preview(showSystemUi = true)
@Composable
fun QuizScreen(
    viewModel: QuizViewModel = hiltViewModel(),
    onGameFinished: () -> Unit = {}
) {
    val context= LocalContext.current
    val question = viewModel.currentQuestion
    val index = viewModel.currentQuestionIndex.value
    val isFinished = viewModel.isGameFinished.value

    // ✅ Trigger navigation only once when game is finished
    LaunchedEffect(isFinished) {
        if (isFinished) {
            onGameFinished()
        }
    }

    if (isFinished) return

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(painter = painterResource(id = R.drawable.love_bg), contentDescription = "bg", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds)
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color(0x79FFFCFC)))
        Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Question ${index + 1}", fontSize = 16.sp, color = Color.DarkGray, fontFamily = FontFamily.Monospace)
            Spacer(modifier = Modifier.height(12.dp))
            Text(question.text, color = Color.Black, fontSize = 23.sp, fontStyle = FontStyle.Italic, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(24.dp))
            Row {
                Button(onClick = { playSound(context, R.raw.yes_sound) {
                        viewModel.answer(true)
                    } },
                    modifier = Modifier.size(90.dp),
                    shape = CircleShape,
                    elevation = ButtonDefaults.buttonElevation(10.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF008000))) {
                    Text("YES", color = Color.White, fontSize = 21.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Serif)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {playSound(context, R.raw.no_sound) {
                    viewModel.answer(false)
                } },
                    modifier = Modifier.size(90.dp),
                    shape = CircleShape,
                    elevation = ButtonDefaults.buttonElevation(10.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFCC0D0D))) {
                    Text("NO", color = Color.White, fontSize = 21.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Serif)
                }
            }
        }
    }
}

