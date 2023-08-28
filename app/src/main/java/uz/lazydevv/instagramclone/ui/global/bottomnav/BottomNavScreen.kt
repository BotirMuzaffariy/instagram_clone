package uz.lazydevv.instagramclone.ui.global.bottomnav

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import uz.lazydevv.instagramclone.R
import uz.lazydevv.instagramclone.ui.global.bottomnav.home.HomeScreen
import uz.lazydevv.instagramclone.ui.global.bottomnav.profile.ProfileScreen
import uz.lazydevv.instagramclone.ui.global.bottomnav.reels.ReelsScreen
import uz.lazydevv.instagramclone.ui.global.bottomnav.search.SearchScreen
import uz.lazydevv.instagramclone.ui.theme.myColorScheme

@Composable
fun BottomNavScreen() {
    val navController = rememberNavController()

    val bottomNavItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Add,
        BottomNavItem.Reels,
        BottomNavItem.Profile
    )

    Scaffold(
        bottomBar = {
            MainBottomNavigation(navController, bottomNavItems)
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = BottomNavItem.Home.route
        ) {
            composable(BottomNavItem.Home.route) {
                HomeScreen()
            }

            composable(BottomNavItem.Search.route) {
                SearchScreen()
            }

            composable(BottomNavItem.Add.route) {
                // todo: add screen
            }

            composable(BottomNavItem.Reels.route) {
                ReelsScreen()
            }

            composable(BottomNavItem.Profile.route) {
                ProfileScreen()
            }
        }
    }
}

@Composable
private fun MainBottomNavigation(
    navController: NavHostController,
    items: List<BottomNavItem>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedItemRoute = navBackStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp)
            .background(myColorScheme.background),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                modifier = Modifier.weight(1f),
                item = item,
                selected = selectedItemRoute == item.route,
                onClick = {
                    if (selectedItemRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun BottomNavigationItem(
    modifier: Modifier = Modifier,
    item: BottomNavItem,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .clickable(
                indication = null,
                interactionSource = MutableInteractionSource()
            ) {
                onClick.invoke()
            },
        contentAlignment = Alignment.Center
    ) {
        if (item.route == BottomNavItem.Profile.route) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(
                        color = if (selected) myColorScheme.onBackground else Color.Transparent,
                        shape = CircleShape
                    )
                    .padding((1.5).dp)
            ) {
                Image(
                    modifier = Modifier.clip(CircleShape),
                    painter = painterResource(id = R.drawable.sample_profile_img1),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        } else {
            Icon(
                modifier = Modifier.size(26.dp),
                painter = painterResource(
                    id = if (selected) item.focusedIcon else item.icon
                ),
                contentDescription = null,
            )
        }
    }
}

@Preview(showSystemUi = true, device = "id:pixel_3a")
@Composable
private fun BottomNavScreenPreview() {
    BottomNavScreen()
}