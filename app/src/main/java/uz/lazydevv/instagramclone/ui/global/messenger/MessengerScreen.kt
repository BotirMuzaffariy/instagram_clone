package uz.lazydevv.instagramclone.ui.global.messenger

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import uz.lazydevv.instagramclone.R
import uz.lazydevv.instagramclone.models.MessageM
import uz.lazydevv.instagramclone.ui.global.bottomnav.components.StoryAvatar
import uz.lazydevv.instagramclone.ui.global.bottomnav.components.StoryAvatarType
import uz.lazydevv.instagramclone.ui.global.components.CustomSearchBar
import uz.lazydevv.instagramclone.ui.global.components.UsernameActionBar
import uz.lazydevv.instagramclone.ui.theme.Colors
import uz.lazydevv.instagramclone.ui.theme.myColorScheme
import uz.lazydevv.instagramclone.utils.MockData

@Composable
fun MessengerScreen(
    globalNavHostController: NavHostController
) {
    val lazyListState = rememberLazyListState()

    val shouldShowDivider by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex != 0
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(myColorScheme.background)
    ) {
        UsernameActionBar(
            withBackBtn = true,
            btn1Icon = R.drawable.ic_add_camera,
            btn2Icon = R.drawable.ic_edit,
            onBtnBackClick = {
                globalNavHostController.popBackStack()
            }
        )

        if (shouldShowDivider) Divider(thickness = .5.dp)

        LazyColumn(state = lazyListState) {
            item {
                Spacer(modifier = Modifier.height(.2.dp))
            }

            item {
                CustomSearchBar(
                    modifier = Modifier.padding(0.dp, 12.dp)
                )
            }

            item {
                MessagesSection()
            }
        }
    }
}

@Composable
private fun MessagesSection() {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp, 8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.messages),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = stringResource(id = R.string.requests),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Colors.blue,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }

        MockData.messages.forEachIndexed { index, msg ->
            MessageItem(msg = msg, pos = index)
        }
    }
}

@Composable
private fun MessageItem(
    msg: MessageM,
    pos: Int
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(14.dp, 6.dp)
    ) {
        val (cAvatar, cUsername, cLastMsg, cTimestamp,
            cSeenIcon, cIcon) = createRefs()

        StoryAvatar(
            avatarImg = msg.user.avatarImg,
            avatarSize = StoryAvatarType.MESSAGE_HEADER,
            shouldShowStoryCircle = pos % 4 == 0,
            modifier = Modifier.constrainAs(cAvatar) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        )

        createVerticalChain(
            cUsername, cLastMsg,
            chainStyle = ChainStyle.Packed
        )

        Text(
            text = msg.user.username,
            fontSize = 13.sp,
            fontWeight = if (msg.isSeen) FontWeight.Normal else FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(cUsername) {
                width = Dimension.fillToConstraints

                start.linkTo(cAvatar.end, 10.dp)
                end.linkTo(cSeenIcon.start, 10.dp)
            }
        )

        Text(
            text = msg.lastMessage,
            fontSize = 13.sp,
            fontWeight = if (msg.isSeen) FontWeight.Normal else FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = if (msg.isSeen) Colors.onBgSearch else Color.Unspecified,
            modifier = Modifier.constrainAs(cLastMsg) {
                start.linkTo(cUsername.start)
            }
        )

        Text(
            text = " • ${msg.timeStamp}",
            fontSize = 13.sp,
            color = if (msg.isSeen) Colors.onBgSearch else Color.Unspecified,
            modifier = Modifier.constrainAs(cTimestamp) {
                linkTo(
                    top = cLastMsg.top,
                    bottom = cLastMsg.bottom,
                    start = cLastMsg.end,
                    end = cSeenIcon.start,
                    endMargin = 14.dp,
                    horizontalBias = 0f
                )
            }
        )

        Spacer(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(
                    if (msg.isSeen) Color.Transparent else Colors.blue
                )
                .constrainAs(cSeenIcon) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(cIcon.start, 12.dp)
                }
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_camera),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .constrainAs(cIcon) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}

@Preview(showSystemUi = true, device = "id:pixel_3a")
@Composable
private fun MessengerScreenPreview() {
    MessengerScreen(rememberNavController())
}