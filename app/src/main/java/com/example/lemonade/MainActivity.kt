package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Lemonade()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Lemonade(modifier: Modifier = Modifier) {
    var counter: Int by remember { mutableIntStateOf(0) }
    var squeezeCounter: Int by remember { mutableIntStateOf(0) }
    var squeezesNeeded: Int = (1 .. 3).random()

    val stringResource: Int
    val imageResource: Int
    val contentResource: Int
    when (counter) {
        0 -> {
            stringResource = R.string.lemon_tree
            imageResource = R.drawable.lemon_tree
            contentResource = R.string.lemon_tree_content
        }
        1 -> {
            stringResource = R.string.squeeze
            imageResource = R.drawable.lemon_squeeze
            contentResource = R.string.lemon_content
        }
        2 -> {
            stringResource = R.string.drink
            imageResource = R.drawable.lemon_drink
            contentResource = R.string.glass_content
        }
        3 -> {
            stringResource = R.string.empty_glass
            imageResource = R.drawable.lemon_restart
            contentResource = R.string.empty_glass_content
        }
        else -> {
            stringResource = R.string.lemon_tree
            imageResource = R.drawable.lemon_tree
            contentResource = R.string.lemon_tree_content
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(
                    red = 195,
                    green = 236,
                    blue = 210
                )
            ),
            shape = RoundedCornerShape(20),
            onClick = {
                if (counter == 1) {
                    if (squeezeCounter != squeezesNeeded) {
                        squeezeCounter += 1
                    } else {
                        squeezeCounter = 0
                        squeezesNeeded = (1 .. 3).random()
                        counter = 2
                    }
                } else if (counter >= 3) {
                    counter = 0
                } else {
                    counter += 1
                }
            }
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = stringResource(id = contentResource)
            )
        }
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = stringResource(id = stringResource),
            fontSize = 18.sp
        )
    }
}