package com.example.levelup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.levelup.ui.theme.LevelUPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LevelUPTheme {
                HomeScreen() // 👈 onCreate is now just 1 line!
            }
        }
    }
}

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
        CopyRights()
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
fun CopyRights() {
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
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Button( onClick = { /*TODO*/ }){
            Text(
                color = Color.Black,
                modifier = Modifier.alpha(0.3f),
                text = "Play",
                style = TextStyle(fontSize = 15.sp)
            )
        }
        Button( onClick = { /*TODO*/ }) {
            Text(
                color = Color.Black,
                modifier = Modifier.alpha(0.3f),
                text = "Replay",
                style = TextStyle(fontSize = 15.sp)
            )
        }
    }
}