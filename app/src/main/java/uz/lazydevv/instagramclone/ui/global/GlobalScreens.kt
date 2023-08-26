package uz.lazydevv.instagramclone.ui.global

sealed class GlobalScreens(val route: String) {

    object BottomNavScreen: GlobalScreens("bottom_nav_screen")

    object Messages: GlobalScreens("messages")
}