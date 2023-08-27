package uz.lazydevv.instagramclone.ui.global.bottomnav.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import uz.lazydevv.instagramclone.R
import uz.lazydevv.instagramclone.extensions.clickableWithoutRipple
import uz.lazydevv.instagramclone.models.MediaM
import uz.lazydevv.instagramclone.models.UserM
import uz.lazydevv.instagramclone.utils.MockData

@Composable
fun RowFeedMedia(
    mediaItem: MediaM
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        MediaTitle(
            user = mediaItem.user,
            secondaryLabel = mediaItem.secondaryLabel
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.2.dp)
                .background(Color.LightGray)
        )

        Image(
            painter = painterResource(id = mediaItem.postImg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f / 1f)
        )
    }
}

@Composable
private fun MediaTitle(
    user: UserM,
    secondaryLabel: String = ""
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 8.dp)
    ) {
        val (ivAvatar, tvUsername, tvSecondaryLabel, ivMore) = createRefs()

        StoryAvatar(
            modifier = Modifier
                .padding(start = 12.dp)
                .constrainAs(ivAvatar) {},
            avatarImg = user.avatarImg,
            avatarSize = StoryAvatarSize.SMALL,
            shouldShowStoryCircle = user.hasActiveStory,
            isSeen = false
        )

        createVerticalChain(
            tvUsername, tvSecondaryLabel,
            chainStyle = ChainStyle.Packed
        )

        Text(
            text = user.username,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .constrainAs(tvUsername) {
                    width = Dimension.fillToConstraints

                    start.linkTo(ivAvatar.end, 6.dp)
                    top.linkTo(parent.top)
                    end.linkTo(ivMore.start, 6.dp)
                    bottom.linkTo(tvSecondaryLabel.top)
                }
        )

        if (secondaryLabel.isNotEmpty()) {
            Text(
                text = secondaryLabel,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.constrainAs(tvSecondaryLabel) {
                    width = Dimension.fillToConstraints

                    start.linkTo(tvUsername.start)
                    top.linkTo(tvUsername.bottom)
                    end.linkTo(tvUsername.end)
                    bottom.linkTo(ivAvatar.bottom)
                }
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_more),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .clickableWithoutRipple { /*todo*/ }
                .constrainAs(ivMore) {
                    height = Dimension.fillToConstraints
                    width = Dimension.ratio("H, 1:0.8")

                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end, 4.dp)
                }
                .padding(6.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RowFeedMediaPreview() {
    RowFeedMedia(MockData.rowMediaItems[0])
}