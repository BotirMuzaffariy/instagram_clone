package uz.lazydevv.instagramclone.ui.global.bottomnav.reels

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import uz.lazydevv.instagramclone.R
import uz.lazydevv.instagramclone.extensions.clickableWithoutRipple
import uz.lazydevv.instagramclone.models.MediaM
import uz.lazydevv.instagramclone.ui.global.bottomnav.components.StoryAvatar
import uz.lazydevv.instagramclone.ui.global.bottomnav.components.StoryAvatarType
import uz.lazydevv.instagramclone.ui.theme.Colors
import uz.lazydevv.instagramclone.utils.MockData

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReelsScreen() {
    val mediaItems = MockData.rowMediaItems

    val pagerState = rememberPagerState(pageCount = { mediaItems.size })

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        VerticalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { pageIndex ->
            DrawReel(
                media = mediaItems[pageIndex],
                shouldShowFollowBtn = pageIndex != 0,
                position = pageIndex + 1
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(Colors.black30, Color.Transparent)
                    )
                )
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_camera),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .padding(top = 6.dp, end = 8.dp)
                .size(44.dp)
                .align(Alignment.TopEnd)
                .padding(8.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DrawReel(
    media: MediaM,
    shouldShowFollowBtn: Boolean = true,
    position: Int
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        var isShowFullDesc by remember { mutableStateOf(false) }

        val (c0, cAuthorInfo, cDescription, cOriginalAudio,
            cActions, cMore, cAlbumArt) = createRefs()

        Image(
            painter = painterResource(id = media.postImg),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clickableWithoutRipple { isShowFullDesc = false }
                .background(Color.Black)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(Color.Transparent, Colors.black50)
                    )
                )
                .constrainAs(c0) {
                    bottom.linkTo(parent.bottom)
                }
        )

        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.constrainAs(cActions) {
                end.linkTo(parent.end, 12.dp)
                bottom.linkTo(cMore.top, 4.dp)
            }
        ) {
            ReelAction(icon = R.drawable.ic_heart, count = "${position * 109}M")

            ReelAction(icon = R.drawable.ic_comment, count = "${position * 41}K")

            ReelAction(icon = R.drawable.ic_send, count = "${position * 97}M")
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_more),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(36.dp)
                .padding(6.dp)
                .constrainAs(cMore) {
                    start.linkTo(cActions.start)
                    end.linkTo(cActions.end)
                    bottom.linkTo(cAlbumArt.top, 14.dp)
                }
        )

        Image(
            painter = painterResource(id = media.user.avatarImg),
            contentDescription = null,
            modifier = Modifier
                .size(26.dp)
                .background(Color.White, RoundedCornerShape(4.dp))
                .padding(2.dp)
                .clip(RoundedCornerShape(3.dp))
                .constrainAs(cAlbumArt) {
                    bottom.linkTo(parent.bottom, 18.dp)
                    start.linkTo(cActions.start)
                    end.linkTo(cActions.end)
                }
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.constrainAs(cAuthorInfo) {
                width = Dimension.fillToConstraints

                start.linkTo(cOriginalAudio.start)
                end.linkTo(cActions.start)
                bottom.linkTo(cDescription.top, 18.dp)
            }
        ) {
            StoryAvatar(
                avatarImg = media.user.avatarImg,
                avatarSize = StoryAvatarType.REEL_AUTHOR_INFO,
                shouldShowStoryCircle = true
            )

            Text(
                text = media.user.username,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                modifier = Modifier.padding(8.dp, 0.dp)
            )

            if (shouldShowFollowBtn) {
                Text(
                    text = "Follow",
                    color = Color.White,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .border(.6.dp, Color.White, RoundedCornerShape(8.dp))
                        .padding(16.dp, 4.dp)
                )
            }
        }

        Text(
            text = media.description,
            fontSize = 14.sp,
            color = Color.White,
            maxLines = if (isShowFullDesc) Int.MAX_VALUE else 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .constrainAs(cDescription) {
                    width = Dimension.fillToConstraints

                    start.linkTo(cOriginalAudio.start)
                    end.linkTo(cActions.start, 8.dp)
                    bottom.linkTo(cOriginalAudio.top, 12.dp)
                }
                .clickableWithoutRipple { isShowFullDesc = !isShowFullDesc }
                .animateContentSize()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .constrainAs(cOriginalAudio) {
                    width = Dimension.percent(0.45f)

                    start.linkTo(parent.start, 16.dp)
                    bottom.linkTo(cAlbumArt.bottom, (-6).dp)
                }
                .background(Colors.onBgSearch40, CircleShape)
                .padding(8.dp, 4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_music_note),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(14.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "${media.user.username} • Original audio",
                fontSize = 11.sp,
                color = Color.White,
                maxLines = 1,
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .basicMarquee(
                        iterations = Int.MAX_VALUE,
                        delayMillis = 500,
                        velocity = 40.dp,
                        spacing = MarqueeSpacing(20.dp)
                    )
            )
        }
    }
}

@Composable
private fun ReelAction(
    icon: Int,
    count: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(36.dp)
                .padding(4.dp)
        )

        Text(
            text = count,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
    }
}

@Preview(showSystemUi = true, device = "id:pixel_3a")
@Composable
private fun ReelsScreenPreview() {
    ReelsScreen()
}