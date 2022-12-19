package com.shii.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shii.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                LemonadeApp()
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeWithImageButton(
        Modifier
            .fillMaxSize()
            .wrapContentSize()
    )
}

@Composable
fun LemonadeWithImageButton(modifier: Modifier = Modifier) {
    var currentState by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(2) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val currentStateText: String
        val currentStateImage: Painter
        val currentStateContentDescription: String

        when (currentState) {
            1 -> {
                currentStateText = stringResource(id = R.string.lemon_tree_text)
                currentStateImage = painterResource(id = R.drawable.lemon_tree)
                currentStateContentDescription = stringResource(id = R.string.lemon_tree_content_description)
            }
            2 -> {
                currentStateText = stringResource(id = R.string.lemon_text)
                currentStateImage = painterResource(id = R.drawable.lemon_squeeze)
                currentStateContentDescription = stringResource(id = R.string.lemon_content_description)

                squeezeCount = (2..4).random()
            }
            3 -> {
                currentStateText = stringResource(id = R.string.glass_of_lemon_text)
                currentStateImage = painterResource(id = R.drawable.lemon_drink)
                currentStateContentDescription = stringResource(id = R.string.glass_of_lemon_content_description)
            }
            else -> {
                currentStateText = stringResource(id = R.string.empty_glass_text)
                currentStateImage = painterResource(id = R.drawable.lemon_restart)
                currentStateContentDescription = stringResource(id = R.string.empty_glass_content_description)
            }
        }

        Text(text = currentStateText, fontSize = 18.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = currentStateImage,
            contentDescription = currentStateContentDescription,
            modifier = Modifier
                .clickable {
                    when (currentState) {
                        2 -> {
                            if (squeezeCount == 0) {
                                currentState++
                            } else {
                                squeezeCount--
                            }
                        }
                        4 -> {
                            currentState = 1
                        }
                        else -> {
                            currentState++
                        }
                    }
                }
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
    }
}
