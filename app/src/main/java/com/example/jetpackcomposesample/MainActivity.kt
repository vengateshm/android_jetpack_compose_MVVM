package com.example.jetpackcomposesample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrollableColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFFFF2F2F2))
            ) {
                Column {
                    Image(
                        bitmap = imageResource(id = R.drawable.burger),
                        modifier = Modifier.height(300.dp),
                        contentScale = ContentScale.Crop
                    )
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Juicy Cheesy Burger",
                            style = TextStyle(fontSize = TextUnit.Companion.Sp(26))
                        )
                        Spacer(modifier = Modifier.padding(top = 10.dp))
                        Text(
                            text = "1000 Calories",
                            style = TextStyle(fontSize = TextUnit.Companion.Sp(17))
                        )
                        Spacer(modifier = Modifier.padding(top = 10.dp))
                        Text(
                            text = "$9.9",
                            style = TextStyle(
                                fontSize = TextUnit.Companion.Sp(17),
                                color = Color(0xFF85bb65)
                            )
                        )
                    }
                }
            }
        }
    }
}