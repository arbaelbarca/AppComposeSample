package com.arbaelbarca.appcomposesample.ui.presentation.bottomnavigation.menu

import com.arbaelbarca.appcomposesample.R

sealed class BottomNavItemMenu(
    val title: String,
    var icon: Int,
    var routeScreen: String
){
    object Home : BottomNavItemMenu("Home", R.drawable.ic_baseline_home_24,"home")
    object MyNetwork: BottomNavItemMenu("My Network",R.drawable.ic_baseline_home_24,"my_network")
    object AddPost: BottomNavItemMenu("Post",R.drawable.ic_baseline_home_24,"add_post")
}
