package uz.lazydevv.instagramclone.ui.global.bottomnav.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import uz.lazydevv.instagramclone.R
import uz.lazydevv.instagramclone.extensions.clickableWithoutRipple
import uz.lazydevv.instagramclone.ui.global.bottomnav.home.components.StoryAvatar
import uz.lazydevv.instagramclone.ui.global.bottomnav.home.components.StoryAvatarType
import uz.lazydevv.instagramclone.ui.theme.Colors
import uz.lazydevv.instagramclone.utils.MockData

@Composable
fun ProfileScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        ProfileActionBar()

        LazyColumn {
            item {
                ProfileHeader()
            }

            item {
                Spacer(modifier = Modifier.height(4.dp))

                ProfileHeaderActions()
            }

            item {
                HighlightStories(modifier = Modifier.padding(0.dp, 20.dp))
            }
        }
    }
}

@Composable
private fun ProfileActionBar(
    modifier: Modifier = Modifier,
    onCreateClick: () -> Unit = {},
    onOptionsClick: () -> Unit = {}
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        val (tvUsername, iv0, ivCreate, ivOptions) = createRefs()

        Text(
            text = "lazydevv",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.constrainAs(tvUsername) {
                start.linkTo(parent.start, 16.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_keyboard_arrow_down),
            contentDescription = null,
            modifier = Modifier
                .size(16.dp)
                .constrainAs(iv0) {
                    start.linkTo(tvUsername.end, 4.dp)
                    top.linkTo(tvUsername.top, 4.dp)
                    bottom.linkTo(tvUsername.bottom)
                }
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_add_box),
            contentDescription = null,
            modifier = Modifier
                .clickableWithoutRipple { onCreateClick() }
                .constrainAs(ivCreate) {
                    height = Dimension.matchParent
                    width = Dimension.ratio("H, 1:0.8")

                    top.linkTo(tvUsername.top)
                    bottom.linkTo(tvUsername.bottom)
                    end.linkTo(ivOptions.start)
                }
                .padding(9.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = null,
            modifier = Modifier
                .clickableWithoutRipple { onOptionsClick() }
                .constrainAs(ivOptions) {
                    height = Dimension.matchParent
                    width = Dimension.ratio("H, 1:0.8")

                    top.linkTo(tvUsername.top)
                    bottom.linkTo(tvUsername.bottom)
                    end.linkTo(parent.end, 8.dp)
                }
                .padding(10.dp)
        )
    }
}

@Composable
private fun ProfileHeader() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp, 8.dp)
    ) {
        val (ivAvatar, tvName, tvBio, vInfo, vThreads) = createRefs()

        StoryAvatar(
            avatarImg = R.drawable.sample_profile_img1,
            avatarSize = StoryAvatarType.PROFILE_IMG,
            shouldShowStoryCircle = false,
            modifier = Modifier.constrainAs(ivAvatar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.constrainAs(vInfo) {
                width = Dimension.percent(0.62f)

                linkTo(
                    start = ivAvatar.end,
                    end = parent.end,
                    top = ivAvatar.top,
                    bottom = ivAvatar.bottom,
                    endMargin = 8.dp,
                    horizontalBias = 1f,
                )
            }
        ) {
            InfoContainer(
                count = "5",
                label = "Posts"
            )

            InfoContainer(
                count = "571M",
                label = "Followers"
            )

            InfoContainer(
                count = "3",
                label = "Following"
            )
        }

        Text(
            text = "Just Botir",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(tvName) {
                top.linkTo(ivAvatar.bottom)
                start.linkTo(ivAvatar.start)
            }
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Colors.grayLight, RoundedCornerShape(12.dp))
                .constrainAs(vThreads) {
                    top.linkTo(tvName.bottom, 5.dp)
                    start.linkTo(tvName.start)
                }
                .padding(8.dp, 4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_logo_threads),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(14.dp)
            )

            Text(
                text = "97,365,821",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.sp,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        Text(
            text = "Life goes on...",
            fontSize = 14.sp,
            modifier = Modifier.constrainAs(tvBio) {
                width = Dimension.fillToConstraints

                top.linkTo(vThreads.bottom, 1.dp)
                start.linkTo(tvName.start)
                end.linkTo(parent.end)
            }
        )
    }
}

@Composable
private fun InfoContainer(
    count: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = count,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = label,
            fontSize = 14.sp,
        )
    }
}

@Composable
private fun ProfileHeaderActions() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp, 0.dp)
    ) {
        Text(
            text = stringResource(id = R.string.edit_profile),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1f)
                .background(Colors.grayLight, RoundedCornerShape(8.dp))
                .padding(0.dp, 6.dp)
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = stringResource(id = R.string.share_profile),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1f)
                .background(Colors.grayLight, RoundedCornerShape(8.dp))
                .padding(0.dp, 6.dp)
        )
    }
}

@Composable
private fun HighlightStories(
    modifier: Modifier = Modifier
) {
    val highlightStories = MockData.highlightStories

    LazyRow(
        modifier = modifier
    ) {
        itemsIndexed(highlightStories) { index, story ->
            if (index == 0) Spacer(modifier = Modifier.width(12.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                StoryAvatar(
                    avatarImg = story.img,
                    avatarSize = StoryAvatarType.HIGHLIGHT_STORY,
                    shouldShowStoryCircle = true,
                    isSeen = true
                )

                Text(
                    text = story.title,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))
        }

        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(StoryAvatarType.HIGHLIGHT_STORY.size + 2.dp)
                        .border(1.dp, Color.Black, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_plus),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Text(
                    text = stringResource(id = R.string.t_new),
                    fontSize = 13.sp,
                    modifier = Modifier.padding(top = 7.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))
        }
    }
}

@Preview(showSystemUi = true, device = "id:pixel_3a")
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen()
}