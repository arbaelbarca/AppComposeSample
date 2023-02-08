package com.arbaelbarca.appcomposesample.ui.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arbaelbarca.appcomposesample.ui.theme.AppComposeSampleTheme

class TestUiComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppComposeSampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    InitialMainRoute()
                }
            }
        }
    }
}

@Preview(
    name = "test",
    showSystemUi = true
)
@Composable
fun InitialMainRoute() {

    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            text = "Hello Test Maxwith"
        )

        Column(
            Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            MyListString()
        }
    }

}

@Composable
fun MyListString() {
    val listCustomTest: MutableList<String> = mutableListOf()

    listCustomTest.add(0, "test")
    listCustomTest.add(1, "Test kedua")
    listCustomTest.add(2, "test lagi")

    LazyColumn(
        modifier = Modifier
            .background(Color.Black)
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        items(listCustomTest) {
            val getData = it
            MyCardText(getText = getData)
        }
    }
}

@Composable
fun MyCardText(
    getText: String
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = getText
        )
    }
}