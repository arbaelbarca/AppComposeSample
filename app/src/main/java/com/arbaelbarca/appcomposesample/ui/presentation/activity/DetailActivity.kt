package com.arbaelbarca.appcomposesample.ui.presentation.activity

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.arbaelbarca.appcomposesample.domain.model.Meals
import com.arbaelbarca.appcomposesample.ui.theme.AppComposeSampleTheme


class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppComposeSampleTheme() {
                Surface(
                    Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val getDataMeals = intent.getSerializableExtra("data") as Meals
                    InitDetailRoute(getMeals = getDataMeals)
                }
            }
        }
    }


}

@Composable
private fun InitDetailRoute(
    getMeals: Meals
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        initToolbar()
        getDataMeals(getMeals = getMeals)
    }
}

@Composable
fun getDataMeals(
    getMeals: Meals
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            modifier = Modifier.padding(10.dp)
        ) {
            AsyncImage(
                model = getMeals.strMealThumb,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15.dp))
            )
        }

        Text(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            text = getMeals.strMeal
        )
    }
}

@Composable
fun initToolbar() {
    val activity = (LocalContext.current as? Activity)

    TopAppBar(
        title = {
            Text(text = "Detail ")
        },
        navigationIcon = {
            IconButton(onClick = {
                activity?.finish()
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}