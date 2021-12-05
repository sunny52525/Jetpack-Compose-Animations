package com.example.revealanimation.screens

import android.os.Looper
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun TelegramStickerScroll() {

    var widthZoomed by remember {
        mutableStateOf(false)
    }
    val height by animateDpAsState(targetValue = if (widthZoomed) 80.dp else 50.dp)
    val width by animateDpAsState(targetValue = if (widthZoomed) 60.dp else 40.dp)
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .height(height)
            .animateContentSize()
            .pointerInput(Unit) {

                detectTapGestures(
                    onPress = {
                        widthZoomed = widthZoomed.not()
                        tryAwaitRelease()

                        android.os

                            .Handler(Looper.getMainLooper())
                            .postDelayed({
                                widthZoomed = widthZoomed.not()
                            }, 2000)
                    }
                ) {

                }
            },
        content = {
            repeat(10) {
                item {
                    StickerThumbnail(width = width)

                }
            }
        })
}

@Composable
fun StickerThumbnail(
    width: Dp
) {
    Spacer(modifier = Modifier.width(10.dp))
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(width)
            .background(Color.White)
    ) {

    }
}