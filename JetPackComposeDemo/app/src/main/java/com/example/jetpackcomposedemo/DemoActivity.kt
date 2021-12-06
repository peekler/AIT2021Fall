package com.example.jetpackcomposedemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.util.*

class DemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                //HelloWorld()
                //ButtonShowTime()
                //TextFieldDemo()
                DemoCard("Hello BME")
            }
        }
    }

    @Composable
    fun TextFieldDemo() {
        Column(Modifier.padding(16.dp)) {
            val textState = remember { mutableStateOf(TextFieldValue()) }
            TextField(
                value = textState.value,
                onValueChange = { textState.value = it }
            )
            Text("The textfield has this text: " + textState.value.text)
        }
    }

    @Composable
    fun ButtonShowTime() {
        Button(
            onClick = {
                Toast.makeText(this, Date(System.currentTimeMillis()).toString(), Toast.LENGTH_LONG)
                    .show()
            },
            modifier = Modifier.padding(Dp(10f))
        ) {
            Text("Show time")
        }
    }

    @Composable
    fun HelloWorld() {
        Column {
            Text("Hello BME")
            Text(Date(System.currentTimeMillis()).toString())
        }
    }



    @Composable
    fun DemoCard(name: String) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .clickable {
                    Toast
                        .makeText(
                            this,
                            Date(System.currentTimeMillis()).toString(),
                            Toast.LENGTH_LONG
                        )
                        .show()
                },
            elevation = 10.dp,
            backgroundColor = Color(255,248,190)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
                Image(
                    painter = painterResource(R.drawable.person),
                    contentDescription = "Person",
                    Modifier.size(50.dp, 50.dp),
                    contentScale = ContentScale.Fit
                )
                Column {
                    Text(name)
                    Text(Date(System.currentTimeMillis()).toString())
                }
            }
        }
    }

}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MaterialTheme {
        Greeting2("Android")
    }
}

