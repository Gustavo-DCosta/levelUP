package com.example.levelup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SportCard(
    sportName: String,
    labelOnRight: Boolean,
    viewModel: SportViewModel  // add this
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(Color(0xFF2C3680))
            .clickable { viewModel.sendSport(sportName) }  // update this
    ) {
        Box(
            modifier = Modifier
                .align(if (labelOnRight) Alignment.BottomEnd else Alignment.BottomStart)
                .padding(12.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFFFF17F))
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = sportName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}