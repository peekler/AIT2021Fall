package com.example.jetpackcomposedemo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.inspector.ParameterType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                /*Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }*/
                ButtonExample()
                //ArtistCard3(Artist("demo", "2021 april"))
                //ClickText()
            }
        }
    }

    @Composable
    fun ClickText() {
        val annotatedText = buildAnnotatedString {
            append("Click ")

            // We attach this *URL* annotation to the following content
            // until `pop()` is called
            pushStringAnnotation(
                tag = "URL",
                annotation = "https://developer.android.com"
            )
            withStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("here")
            }

            pop()
        }

        ClickableText(
            text = annotatedText,
            onClick = { offset ->
                // We check if there is an *URL* annotation attached to the text
                // at the clicked position
                annotatedText.getStringAnnotations(
                    tag = "URL", start = offset,
                    end = offset
                )
                    .firstOrNull()?.let { annotation ->
                        // If yes, we log its value
                        Log.d("Clicked URL", annotation.item)
                        Toast.makeText(this@MainActivity, annotation.item, Toast.LENGTH_LONG).show()
                    }
            }
        )
    }

    @Composable
    fun ButtonExample() {
        Button(onClick = {
            Toast.makeText(this@MainActivity, "Demo", Toast.LENGTH_LONG).show()
        }, colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color.Red
        )) {
            Text("Button")
        }
    }
}


data class Artist(var name: String, var lastSeenOnline: String)

@Composable
fun ArtistCard() {
    Text("Alfred Sisley")
    Text("3 minutes ago")
}

@Composable
fun ArtistCard2() {
    Column {
        Text("Alfred Sisley")
        Text("3 minutes ago")
    }
}

@Composable
fun ArtistCard3(artist: Artist) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(R.drawable.person),
            contentDescription = "Content description",
            Modifier.size(Dp(50f), Dp(50f)),
            contentScale = ContentScale.Fit
        )
        Column {
            Text(artist.name)
            Text(artist.lastSeenOnline)
        }
    }
}

@Composable
fun LazyRowItemsDemo() {
    LazyColumn() {
        items((1..1000).toList()) {
            Text(text = "Item $it")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        Greeting("Android")
    }
}