package com.arbaelbarca.appcomposesample.ui.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.arbaelbarca.appcomposesample.R
import com.arbaelbarca.appcomposesample.domain.model.Meals
import com.arbaelbarca.appcomposesample.ui.presentation.activity.DetailActivity
import com.arbaelbarca.appcomposesample.ui.presentation.bottomnavigation.menu.BottomNavItemMenu
import com.arbaelbarca.appcomposesample.ui.presentation.model.MainEventState
import com.arbaelbarca.appcomposesample.ui.presentation.model.MainUiState
import com.arbaelbarca.appcomposesample.ui.presentation.viewmodel.MainViewModel
import com.arbaelbarca.appcomposesample.ui.theme.AppComposeSampleTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppComposeSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainRoute()
                }
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun MainRoute(
    mainViewModel: MainViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val uiState = mainViewModel.uiState.collectAsState()

    MainScreen(
        mainUiState = uiState.value,
        value = mainViewModel.search,
        onValueChange = {
            mainViewModel.onEvent(
                event = MainEventState.SearchOnChange(it)
            )
        },
        onClickSearch = {
            mainViewModel.onEvent(
                event = MainEventState.Search
            )
        },
        onClickItem = {
            Toast.makeText(context, it.strMeal, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("data", it)
            context.startActivity(intent)
            println("respon clikck Row")
        }
    )
}

@Composable
fun MainScreen(
    mainUiState: MainUiState,
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onClickSearch: () -> Unit,
    onClickItem: (Meals) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                all = 16.dp
            )
    ) {

        Search(
            value = value,
            onValueChange = onValueChange,
            onClick = onClickSearch
        )

        Spacer(
            modifier = modifier
                .padding(
                    all = 16.dp
                )
        )

        when (mainUiState) {
            is MainUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }

            is MainUiState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    content = {
                        items(
                            mainUiState.data,
                            key = {
                                it.id
                            }
                        ) {
                            ItemList(
                                name = it.strMeal,
                                image = it.strMealThumb,
                                onClick = {
                                    onClickItem.invoke(it)
                                }
                            )
                        }
                    }
                )
            }

            is MainUiState.Error -> {
                Text(text = mainUiState.error)
            }

            is MainUiState.Empty -> {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center),
                    text = "data kosong nih"
                )
            }

            is MainUiState.Idle -> {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center),
                    text = "Ayo coba cari"
                )
            }
        }


    }

    val navController = rememberNavController()
    InitBottomNavigationView(navController = navController)

//    Scaffold(
//        bottomBar = {
//        }
//    ) {
//
//    }
}

@Composable
fun Search(
    value: String,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            placeholder = {
                Text(text = "Search")
            }
        )

        Spacer(
            modifier = Modifier
                .padding(
                    start = 15.dp
                )
        )

        Button(
            onClick =
            onClick
        ) {
            Text(text = "Searching")
        }
    }
}

@Composable
fun ItemList(
    name: String,
    image: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .clickable {
                onClick.invoke()
            }
    ) {
        AsyncImage(
            model = image,
            contentDescription = name,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(
            modifier = Modifier
                .padding(start = 10.dp)
        )

        Text(
            text = name,
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
        )
    }
}

@Composable
fun InitBottomNavigationView(
    navController: NavController
) {
    val listOfItemBottom = listOf(
        BottomNavItemMenu.Home,
        BottomNavItemMenu.MyNetwork,
        BottomNavItemMenu.AddPost
    )

    BottomNavigation(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.BottomCenter),
        backgroundColor = colorResource(id = R.color.purple_700),
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        listOfItemBottom.forEach {
            BottomNavigationItem(selected = currentRoute == it.routeScreen,
                icon = {
                    Icon(painter = painterResource(id = it.icon), contentDescription = "")
                },
                label = {
                    Text(text = it.title)
                },
                alwaysShowLabel = true,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Gray,
                onClick = {
                    navController.navigate(it.routeScreen) {
                        navController.graph.startDestinationRoute.let { routeScreen ->
                            popUpTo(routeScreen.toString()) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }

    }
}