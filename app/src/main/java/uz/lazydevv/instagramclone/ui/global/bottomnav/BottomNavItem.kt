package uz.lazydevv.instagramclone.ui.global.bottomnav

import uz.lazydevv.instagramclone.R

sealed class BottomNavItem(
    val route: String,
    val icon: Int,
    val focusedIcon: Int,
) {

    object Home : BottomNavItem("home", R.drawable.ic_home, R.drawable.ic_home_fill)

    object Search : BottomNavItem("search", R.drawable.ic_search, R.drawable.ic_search_fill)

    object Add : BottomNavItem("add", R.drawable.ic_add_box, R.drawable.ic_add_box)

    object Reels : BottomNavItem("reels", R.drawable.ic_reels, R.drawable.ic_reels_filled2)

    object Profile : BottomNavItem("profile", 0, 0)
}