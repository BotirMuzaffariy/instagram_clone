package uz.lazydevv.instagramclone.ui.global

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uz.lazydevv.instagramclone.ui.global.bottomnav.BottomNavScreen
import uz.lazydevv.instagramclone.ui.global.messenger.MessengerScreen

@Composable
fun GlobalNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = GlobalScreens.BottomNavScreen.route
    ) {
        composable(GlobalScreens.BottomNavScreen.route) {
            BottomNavScreen()
        }

        composable(GlobalScreens.Messenger.route) {
            MessengerScreen()
        }
    }
}