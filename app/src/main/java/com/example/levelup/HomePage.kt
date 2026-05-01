package com.example.levelup

import androidx.compose.animation.*
import androidx.compose.animation.core.*
// Note: slideInHorizontally / slideOutHorizontally imports no longer needed
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- CRITICAL IMPORTS FOR THE 'by' DELEGATE ---
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun MainNavigation() {
    var currentScreen by remember { mutableStateOf("home") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() }
    ) { innerPadding ->
        // Simple crossfade between screens
        AnimatedContent(
            targetState = currentScreen,
            transitionSpec = {
                fadeIn(tween(300)).togetherWith(fadeOut(tween(300)))
            },
            label = "screenTransition"
        ) { screen ->
            when (screen) {
                "home" -> HomeContent(
                    innerPadding = innerPadding,
                    onPlayClick = { currentScreen = "games" }
                )
                "games" -> GamesScreen(
                    innerPadding = innerPadding,
                    onBackClick = { currentScreen = "home" }
                )
            }
        }
    }
}

@Composable
fun HomeContent(innerPadding: PaddingValues, onPlayClick: () -> Unit) {
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
        HomeButtons(onNavigate = onPlayClick)
        Footer()
    }
}

@Composable
fun HomeButtons(onNavigate: () -> Unit) {
    // Staggered button entrance
    var playVisible by remember { mutableStateOf(false) }
    var replayVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(200)
        playVisible = true
        kotlinx.coroutines.delay(120)
        replayVisible = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = 35.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Play button with bounce press effect
            AnimatedVisibility(
                visible = playVisible,
                enter = fadeIn(tween(300)) + scaleIn(
                    initialScale = 0.7f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
            ) {
                BouncyButton(
                    text = "Play",
                    onClick = onNavigate
                )
            }

            // Replay button with slight delay
            AnimatedVisibility(
                visible = replayVisible,
                enter = fadeIn(tween(300)) + scaleIn(
                    initialScale = 0.7f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
            ) {
                BouncyButton(
                    text = "Replay",
                    onClick = { /* TODO: Replay logic */ }
                )
            }
        }
    }
}

/**
 * A button that scales down on press and bounces back — gives satisfying tactile feel.
 */
@Composable
fun BouncyButton(text: String, onClick: () -> Unit) {
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.88f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessHigh
        ),
        label = "buttonScale"
    )

    Button(
        onClick = {
            pressed = true
            onClick()
        },
        modifier = Modifier.scale(scale),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White.copy(alpha = 0.3f),
            contentColor = Color.Black
        )
    ) {
        Text(
            text = text,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
    }

    // Reset press state after a short delay so it can re-trigger
    LaunchedEffect(pressed) {
        if (pressed) {
            kotlinx.coroutines.delay(150)
            pressed = false
        }
    }
}

@Composable
fun GamesScreen(
    innerPadding: PaddingValues = PaddingValues(0.dp),
    onBackClick: (() -> Unit)? = null
) {
    val sports = listOf("Volley-ball", "Tennis", "Badminton")

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            sports.forEachIndexed { index, sport ->
                // Each card slides in from alternating sides with staggered delay
                StaggeredSportCard(
                    sportName = sport,
                    index = index,
                    labelOnRight = index % 2 != 0
                )
            }
        }

        // Optional back button (top-left)
        if (onBackClick != null) {
            var backPressed by remember { mutableStateOf(false) }
            val backScale by animateFloatAsState(
                targetValue = if (backPressed) 0.88f else 1f,
                animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
                label = "backScale"
            )
            LaunchedEffect(backPressed) {
                if (backPressed) {
                    kotlinx.coroutines.delay(150)
                    backPressed = false
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.TopStart
            ) {
                Button(
                    onClick = {
                        backPressed = true
                        onBackClick()
                    },
                    modifier = Modifier.scale(backScale),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White.copy(alpha = 0.25f),
                        contentColor = Color.White
                    )
                ) {
                    Text("← Back", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

/**
 * Wraps SportCard with a staggered slide-in animation.
 * Even-indexed cards slide from the left, odd from the right.
 */
@Composable
fun StaggeredSportCard(sportName: String, index: Int, labelOnRight: Boolean) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(index * 120L + 100L)
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(350)) + slideInHorizontally(
            initialOffsetX = { if (labelOnRight) it / 2 else -it / 2 },
            animationSpec = tween(400, easing = EaseOutQuart)
        )
    ) {
        SportCard(
            sportName = sportName,
            labelOnRight = labelOnRight
        )
    }
}

@Composable
fun TopBar() {
    // TopBar slides down from above on first load
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            initialOffsetY = { -it },
            animationSpec = tween(450, easing = EaseOutQuart)
        ) + fadeIn(tween(400))
    ) {
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
                Text(
                    text = "UP",
                    color = Color(0xFFFFD700),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
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
