package com.example.revealanimation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun RatingBar() {

    Row(Modifier.fillMaxWidth()) {

    }
}


@Composable
@Preview
fun RatingCard() {

    Card(shape = RoundedCornerShape(8.dp), backgroundColor = Color(0xffE9F6F4)) {
        Box(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 15.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "1")
        }
    }
}