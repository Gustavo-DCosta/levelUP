package com.example.levelup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HomeScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() } // 👈 clean!
    ) { innerPadding ->
        HomeContent(innerPadding)
    }
}

@Composable
fun TopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF100074))
            .windowInsetsPadding(WindowInsets.statusBars)
            .height(60.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(modifier = Modifier.padding(start = 16.dp)) {
            Text(text = "Level", color = Color.White, fontSize = 32.sp)
            Text(text = "UP", color = Color(0xFFFFD700), fontSize = 32.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun HomeContent(innerPadding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF5E9DCC),
                        Color(0xFF657CF4),
                        Color(0xFF2C4EFF)
                    )
                )
            )
    ) {
        SportsImage()
        HomeButtons()
        Footer()
    }
}

@Composable
fun SportsImage() {
    Image(
        painter = painterResource(id = R.drawable.levelupappimage),
        contentDescription = "my image",
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-35).dp)
    )
}

@Composable
fun Footer() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(
            onClick = { /* TODO */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF100074))
        ) {
            Text("© 2026 LevelUP", color = Color.White)
        }
    }
}

@Composable
fun HomeButtons() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = (+35).dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(1.0f) // optional: row width
        ) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFFFFF).copy(alpha = 0.3f),
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = "Play",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
            }

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFFFFF).copy(alpha = 0.3f),
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = "Replay",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}