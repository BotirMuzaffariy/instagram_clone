package uz.lazydevv.instagramclone.ui.global.bottomnav.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uz.lazydevv.instagramclone.ui.global.bottomnav.home.components.HomeActionBar

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeActionBar(
            modifier = Modifier.fillMaxWidth(),
            onNewsClick = {
                // todo
            },
            onMessengerClick = {
                // todo
            }
        )
    }
}

@Preview(showSystemUi = true, device = "id:pixel_3a")
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}