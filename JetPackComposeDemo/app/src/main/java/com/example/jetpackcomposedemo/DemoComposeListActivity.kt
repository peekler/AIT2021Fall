package com.example.jetpackcomposedemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposedemo.data.Student
import java.util.*

class DemoComposeListActivity : ComponentActivity() {

    var students = mutableListOf<Student>(
        Student("John Doe", "demo@cia.com"),
        Student("Susan Doe", "hello@bme.hu")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                StudentsList(students)
            }
        }
    }
}

@Composable
fun StudentsList(students: List<Student>) {
    LazyColumn() {
        items(students) {
            StudentCard(it.name, it.email)
        }
    }
}

@Composable
fun StudentCardSimple(name: String, email: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        elevation = 10.dp,
        backgroundColor = Color(255,248,190)
    ) {
        Column {
            Text(name)
            Text(email)
        }
    }
}


@Composable
fun StudentCard(name: String, email: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable {},
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
                Text(email)
            }
        }
    }
}
