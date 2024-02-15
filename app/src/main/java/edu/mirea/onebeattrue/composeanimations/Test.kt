package edu.mirea.onebeattrue.composeanimations

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun Test() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(
                state = rememberScrollState(),
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var isIncreased by rememberSaveable {
            mutableStateOf(false)
        }
        val infiniteFloat = rememberInfiniteTransition(label = "")
        val size = infiniteFloat.animateFloat(
            initialValue = 200f,
            targetValue = 300f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000),
                repeatMode = RepeatMode.Reverse
            ),
            label = ""
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { isIncreased = !isIncreased }
        ) {
            Text(
                text = "Animate size",
            )
        }
        AnimatedContainer(
            text = "Size",
            sizeState = size
        )

        var isRound by rememberSaveable {
            mutableStateOf(false)
        }
        val cornerRadius = animateIntAsState(
            targetValue = if (isRound) 50 else 4, label = "",
        )
        val infiniteTransition = rememberInfiniteTransition()
        val rotate = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000),
                repeatMode = RepeatMode.Restart
            ), label = ""
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { isRound = !isRound }
        ) {
            Text(
                text = "Animate shape",
            )
        }
        AnimatedContainer(
            text = "Shape",
            cornerRadiusPercentState = cornerRadius,
            rotateState = rotate
        )

        var isBordered by rememberSaveable {
            mutableStateOf(false)
        }
        val borderWidth = animateDpAsState(targetValue = if (isBordered) 50.dp else 0.dp)
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { isBordered = !isBordered }
        ) {
            Text(
                text = "Animate border",
            )
        }
        AnimatedContainer(
            text = "Border",
            borderWidthState = borderWidth
        )

        var isRed by rememberSaveable {
            mutableStateOf(false)
        }
        val color = animateColorAsState(targetValue = if (isRed) Color.Magenta else Color.Blue)
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { isRed = !isRed }
        ) {
            Text(
                text = "Animate color",
            )
        }
        AnimatedContainer(
            text = "Color",
            colorState = color
        )

        var isTransparent by rememberSaveable {
            mutableStateOf(false)
        }
        val alpha = animateFloatAsState(targetValue = if (isTransparent) 0f else 1f)
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { isTransparent = !isTransparent }
        ) {
            Text(
                text = "Animate visibility",
            )
        }
        AnimatedContainer(
            text = "Visibility",
            alphaState = alpha
        )
    }
}

@Composable
private fun AnimatedContainer(
    text: String,
    sizeState: State<Float> = mutableStateOf(200f),
    cornerRadiusPercentState: State<Int> = mutableStateOf(4),
    borderWidthState: State<Dp> = mutableStateOf(0.dp),
    colorState: State<Color> = mutableStateOf(Color.Blue),
    alphaState: State<Float> = mutableStateOf(1f),
    rotateState: State<Float> = mutableStateOf(0f)
) {
    Box(
        modifier = Modifier
            .rotate(rotateState.value)
            .alpha(alphaState.value)
            .clip(RoundedCornerShape(cornerRadiusPercentState.value))
            .background(colorState.value)
            .size(sizeState.value.dp)
            .border(width = borderWidthState.value, color = Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White
        )
    }
}