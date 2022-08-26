package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Preview
@Composable
fun LemonadePreview() {
    LemonadeApp()
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    var steps by remember {
        mutableStateOf(1)
    }

    var randomLemon by remember {
        mutableStateOf(0)
    }

    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        when (steps) {
            1 -> LemonadeWithTextAndImage(
                textResource = R.string.tap_tree,
                imageResource = R.drawable.lemon_tree,
                contentDescription = R.string.lemon_tree_content_description,
                onClick = {
                    steps = 2
                    randomLemon = (2..4).random()
                }
            )
            2 -> LemonadeWithTextAndImage(
                textResource = R.string.keep_tapping,
                imageResource = R.drawable.lemon_squeeze,
                contentDescription = R.string.lemon_content_description,
                onClick = {
                    randomLemon--
                    if (randomLemon == 0) {
                        steps = 3
                    }
                }
            )
            3 -> LemonadeWithTextAndImage(
                textResource = R.string.tap_lemon,
                imageResource = R.drawable.lemon_drink,
                contentDescription = R.string.glass_lemonade_content_description,
                onClick = {
                    steps = 4
                }
            )
            4 -> LemonadeWithTextAndImage(
                textResource = R.string.tap_glass,
                imageResource = R.drawable.lemon_restart,
                contentDescription = R.string.empty_glass_content_description,
                onClick = {
                    steps = 1
                }
            )
        }

    }
}

@Composable
fun LemonadeWithTextAndImage(
    modifier: Modifier = Modifier,
    textResource: Int,
    imageResource: Int,
    contentDescription: Int,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = textResource),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(18.dp))
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = stringResource(id = contentDescription),
            modifier = Modifier
                .wrapContentSize()
                .clickable(
                    onClick = onClick
                )
                .border(
                    BorderStroke(2.dp, Color(105, 205, 2016)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
    }
}
