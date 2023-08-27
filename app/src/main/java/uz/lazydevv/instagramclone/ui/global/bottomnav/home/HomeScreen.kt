package uz.lazydevv.instagramclone.ui.global.bottomnav.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.lazydevv.instagramclone.ui.global.bottomnav.home.components.HomeActionBar
import uz.lazydevv.instagramclone.ui.global.bottomnav.home.components.RowFeedMedia
import uz.lazydevv.instagramclone.ui.global.bottomnav.home.components.Story
import uz.lazydevv.instagramclone.utils.MockData

@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
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

        item {
            LazyRow {
                itemsIndexed(MockData.stories) { index, story ->
                    if (index == 0) Spacer(modifier = Modifier.width(8.dp))

                    Story(story = story)

                    if (index == MockData.users.lastIndex) Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }

        itemsIndexed(MockData.rowMediaItems) { index, mediaItem ->
            Spacer(modifier = Modifier.height(8.dp))

            if (index == 0) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.2.dp)
                        .background(Color.LightGray)
                )
            }

            RowFeedMedia(mediaItem)
        }
    }
}

@Preview(showSystemUi = true, device = "id:pixel_3a")
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}