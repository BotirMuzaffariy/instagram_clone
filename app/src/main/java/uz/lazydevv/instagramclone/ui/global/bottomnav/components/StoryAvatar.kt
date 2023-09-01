package uz.lazydevv.instagramclone.ui.global.bottomnav.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uz.lazydevv.instagramclone.ui.theme.Colors
import uz.lazydevv.instagramclone.utils.MockData

@Composable
fun StoryAvatar(
    modifier: Modifier = Modifier,
    avatarImg: Int,
    avatarSize: StoryAvatarType,
    shouldShowStoryCircle: Boolean = false,
    isSeen: Boolean = false
) {
    val gapSize = when (avatarSize) {
        StoryAvatarType.POST_HEADER -> 6.dp
        StoryAvatarType.REEL_AUTHOR_INFO -> 10.dp
        else -> 12.dp
    }

    val borderWidth = when (avatarSize) {
        StoryAvatarType.POST_HEADER -> 1.dp
        StoryAvatarType.REEL_AUTHOR_INFO -> 1.5.dp
        else -> 2.5.dp
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        val storyColors = if (isSeen) {
            listOf(Color.LightGray, Color.LightGray)
        } else listOf(Colors.primaryYellow, Colors.primaryOrange, Colors.primaryPurple)

        Box(
            modifier = Modifier
                .size(avatarSize.size + gapSize)
                .border(
                    width = if (shouldShowStoryCircle) borderWidth else (-1).dp,
                    brush = Brush.linearGradient(
                        colors = storyColors,
                        start = Offset(0f, Float.POSITIVE_INFINITY),
                        end = Offset(Float.POSITIVE_INFINITY, 0f)
                    ),
                    shape = CircleShape
                )
        )

        Image(
            painter = painterResource(id = avatarImg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(avatarSize.size)
                .clip(CircleShape)
        )
    }
}

enum class StoryAvatarType(val size: Dp) {
    PROFILE_IMG(80.dp),
    STORY(74.dp),
    HIGHLIGHT_STORY(62.dp),
    POST_HEADER(30.dp),
    REEL_AUTHOR_INFO(38.dp),
}

@Preview(showBackground = true)
@Composable
private fun StoryAvatarPreview() {
    val story = MockData.stories[1]

    StoryAvatar(
        avatarImg = story.user.avatarImg,
        avatarSize = StoryAvatarType.STORY,
        shouldShowStoryCircle = !story.isEmpty,
        isSeen = story.isSeen
    )
}