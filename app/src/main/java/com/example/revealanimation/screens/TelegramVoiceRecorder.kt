package com.example.revealanimation.screens

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.revealanimation.R
import com.example.revealanimation.ui.theme.GrayWhite
import com.example.revealanimation.ui.theme.TelegramBlue
import com.example.revealanimation.ui.theme.TelegramDarkGray
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun TelegramVoiceRecord() {
    MessageBox()
}


@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun MessageBox(modifier: Modifier = Modifier) {

    var message by remember {
        mutableStateOf("")
    }
    var isRecording by remember {
        mutableStateOf(false)
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(TelegramDarkGray), contentAlignment = Alignment.BottomStart
    ) {


        TextField(
            value = message, onValueChange = {
                if (it.isNotEmpty())
                    isRecording = false
                message = it
            },
            modifier = modifier
                .fillMaxWidth(0.8f)
                .padding(end = 10.dp)
                .zIndex(10f),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic__gif),
                    contentDescription = null,
                    tint = GrayWhite,
                    modifier = Modifier
                        .size(24.dp)

                )
            },
            placeholder = {
                Text(text = "Message", color = GrayWhite)
            },
            colors = TextFieldDefaults.textFieldColors(
                placeholderColor = GrayWhite,
                cursorColor = TelegramBlue,

                textColor = GrayWhite,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = TelegramDarkGray,
                unfocusedIndicatorColor = TelegramDarkGray

            )
        )

        MicBox(isRecording, message.isEmpty()) {
            isRecording = it
        }


    }
}


enum class SwipeDirection(val raw: Int) {
    Left(1),
    Initial(2),
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MicBox(isRecording: Boolean, messageEmpty: Boolean, onRecordingChange: (Boolean) -> Unit = {}) {

    val haptic = LocalHapticFeedback.current
    val sizeAnimated by animateDpAsState(
        targetValue = if (isRecording) {
            80.dp
        } else {
            0.dp
        },
        animationSpec = tween(durationMillis = 200)
    )

    var animationSize by remember { mutableStateOf(Size.Zero) }
    val swipeableState = rememberSwipeableState(SwipeDirection.Initial)
    val density = LocalDensity.current
    val boxSize = 60.dp
    val width = remember(animationSize) {
        if (animationSize.width == 0f) {
            1f
        } else {
            animationSize.width - with(density) { boxSize.toPx() }
        }
    }
    val scope = rememberCoroutineScope()
    if (swipeableState.isAnimationRunning) {
        DisposableEffect(Unit) {
            onDispose {
                when (swipeableState.currentValue) {
                    SwipeDirection.Left -> {
                        println("swipe left")
                    }
                    else -> {

                        return@onDispose
                    }
                }
                scope.launch {
                    onRecordingChange(false)
                    Log.d("TAG", "MicBox: ")
                    swipeableState.animateTo(SwipeDirection.Initial)
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .onSizeChanged { animationSize = Size(it.width.toFloat(), it.height.toFloat()) }

            .swipeable(
                state = swipeableState,
                anchors = mapOf(
                    width / 2 to SwipeDirection.Left,
                    width to SwipeDirection.Initial,

                    ),
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal,
                enabled = messageEmpty
            )
            .background(Color.Transparent),

        ) {
        BoxWithConstraints(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                .pointerInput(messageEmpty) {
                    if (messageEmpty.not()) {
                        return@pointerInput
                    }
                    detectTapGestures(

                        onPress = {
                            onRecordingChange(true)
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            awaitRelease()
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            onRecordingChange(false)

                        }
                    )
                }) {
            Card(
                shape = CircleShape,
                backgroundColor =
                TelegramBlue,
                modifier = Modifier.size(sizeAnimated),
                elevation = 20.dp,
            ) {}
            Column(
                Modifier.size(60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = if (messageEmpty) R.drawable.ic__mic else R.drawable.ic__send),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(34.dp)
                        .padding(1.dp)

                )
            }
        }
    }
}