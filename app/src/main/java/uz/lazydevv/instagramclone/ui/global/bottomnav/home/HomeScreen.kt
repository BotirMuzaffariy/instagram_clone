package uz.lazydevv.instagramclone.ui.global.bottomnav.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.lazydevv.instagramclone.ui.global.bottomnav.home.components.HomeActionBar
import uz.lazydevv.instagramclone.ui.global.bottomnav.home.components.Story

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

        val stories = listOf(
            "lazydevv",
            "joxa_203",
            "abror_10_05",
            "ahror_23",
            "alimardon_22_31",
        )

        LazyRow {
            itemsIndexed(stories) { index, item ->
                if (index == 0) Spacer(modifier = Modifier.width(8.dp))

                Story(
                    item = item,
                    shouldShowAddStory = index == 0
                )

                if (index == stories.lastIndex) Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Preview(showSystemUi = true, device = "id:pixel_3a")
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}