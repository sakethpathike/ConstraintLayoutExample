package com.sakethh.blog_samples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                BoxWithConstraintLayoutExample()
            }
        }
    }
}

@Preview()
@Composable
fun DefaultPreview() {
    MaterialTheme {
        BoxWithConstraintLayoutExample()
    }
}

@Composable
fun ConstraintLayoutExample() {
    val constraintSet = ConstraintSet {
        val composable1 = createRefFor("composable1")
        val composable2 = createRefFor("composable2")
        val composable3 = createRefFor("composable3")
        constrain(composable1) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(composable2) {
            top.linkTo(composable1.bottom)
            start.linkTo(composable1.start)
        }
        constrain(composable3) {
            top.linkTo(composable1.bottom)
            end.linkTo(composable1.end)
        }
    }
    ConstraintLayout(
        constraintSet = constraintSet, modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Box(
            modifier = Modifier
                .padding(top = 20.dp)
                .requiredWidth(250.dp)
                .requiredHeight(100.dp)
                .background(Color.White)
                .layoutId("composable1")
        )
        Box(
            modifier = Modifier
                .padding(top = 20.dp)
                .size(100.dp)
                .background(Color.LightGray)
                .layoutId("composable2")
        )
        Box(
            modifier = Modifier
                .padding(top = 20.dp)
                .size(100.dp)
                .background(Color.LightGray)
                .layoutId("composable3")
        )
    }
}

@Composable
fun BoxWithConstraintLayoutExample() {
    val constraintSet = ConstraintSet {
        val pauseButton = createRefFor("pauseButton")
        val previousTrackButton = createRefFor("previousTrackButton")
        val nextTrackButton = createRefFor("nextTrackButton")
        constrain(pauseButton) {
            top.linkTo(parent.top)  // for this particular example "parent" is "BoxWithConstraints"
            start.linkTo(parent.start)  // for this particular example "parent" is "BoxWithConstraints"
            end.linkTo(parent.end)  // for this particular example "parent" is "BoxWithConstraints"
            bottom.linkTo(parent.bottom)  // for this particular example "parent" is "BoxWithConstraints"
        }
        constrain(previousTrackButton) {
            top.linkTo(pauseButton.top)
            end.linkTo(pauseButton.start)
            bottom.linkTo(pauseButton.bottom)
        }
        constrain(nextTrackButton) {
            top.linkTo(pauseButton.top)
            start.linkTo(pauseButton.end)
            bottom.linkTo(pauseButton.bottom)
        }
    }
    BoxWithConstraints(  //   0 . "BoxWithConstraints"
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        ConstraintLayout( //   1 . "BoxWithConstraints" which contains "ConstraintLayout" stuff!
            constraintSet = constraintSet, modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.DarkGray)
                .align(Alignment.Center)
        ) {
            IconButton(
                modifier = Modifier
                    .layoutId("pauseButton")  // layoutID
                    .background(color = Color.White, shape = CircleShape)
                    .size(55.dp), onClick = {}) {
                Image(
                    painter = painterResource(id = R.drawable.pause_icon_current_music_screen),
                    contentDescription = "",
                    modifier = Modifier.size(35.dp)
                )
            }
            IconButton(
                modifier = Modifier.layoutId("previousTrackButton")  // layoutID
                , onClick = {}) {
                Image(
                    painter = painterResource(id = R.drawable.previoustrack),
                    contentDescription = "",
                    modifier = Modifier.size(40.dp)
                )
            }
            IconButton(modifier = Modifier.layoutId("nextTrackButton"),   // layoutID
                onClick = {}) {
                Image(
                    painter = painterResource(id = R.drawable.nexttrack),
                    contentDescription = "",
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}
