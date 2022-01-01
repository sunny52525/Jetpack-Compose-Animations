package com.example.revealanimation.screens

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.revealanimation.R
import com.example.revealanimation.ui.theme.GrayWhite
import com.example.revealanimation.ui.theme.TelegramBlue
import com.example.revealanimation.ui.theme.TelegramDarkGray

@Composable
fun TelegramVoiceRecord() {


    MessageBox()


}


@Preview
@Composable
fun MessageBox(modifier: Modifier = Modifier) {

    var message by remember {
        mutableStateOf("")
    }

    var isRecording by remember {
        mutableStateOf(false)
    }
    val haptic = LocalHapticFeedback.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(TelegramDarkGray), contentAlignment = Alignment.BottomStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            TextField(
                value = message, onValueChange = {
                    message = it
                },
                modifier = modifier
                    .fillMaxWidth(0.8f)
                    .padding(end = 10.dp),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic__gif),
                        contentDescription = null,
                        tint = GrayWhite,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                isRecording = isRecording.not()
                            }
                    )
                },
                placeholder = {
                    Text(text = "Message", color = GrayWhite)
                },
                colors = TextFieldDefaults.textFieldColors(
                    placeholderColor = GrayWhite,
                    cursorColor = TelegramBlue,

                    textColor = GrayWhite,
                    backgroundColor = TelegramDarkGray,
                    focusedIndicatorColor = TelegramDarkGray,
                    unfocusedIndicatorColor = TelegramDarkGray

                )
            )
            val context = LocalContext.current

            if (message.isEmpty()) {

                RecordingMicIcon(isRecording = isRecording, modifier = Modifier
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                isRecording = true

                                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                awaitRelease()
                                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                isRecording = false
                            }
                        )
                    }
                    .fillMaxWidth(1f)
                    .height(80.dp))


            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic__send),
                    contentDescription = null,
                    tint = TelegramBlue,
                    modifier = Modifier
                        .size(24.dp)
                        .fillMaxWidth(1f)

                )

            }
        }
    }
}


@Preview
@Composable
fun RecordingMicIcon(modifier: Modifier = Modifier, isRecording: Boolean = false) {


    val sizeAnimated by animateDpAsState(
        targetValue = if (isRecording) {
            1000.dp
        } else {
            0.dp
        },
        animationSpec = tween(durationMillis = 300)
    )

    Log.d("TAG", "RecordingMicIcon: $isRecording")
    BoxWithConstraints(contentAlignment = Alignment.Center, modifier = modifier) {


        Card(
            shape = CircleShape,
            backgroundColor =
            TelegramBlue,
            modifier = Modifier.size(sizeAnimated),
            elevation = 20.dp,
        ) {}
        Column(
            modifier.size(60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic__mic), contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(34.dp)
                    .padding(1.dp)

            )
        }
    }
}