package uz.lazydevv.instagramclone.ui.global.bottomnav.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import uz.lazydevv.instagramclone.R

@Composable
fun Story(
    item: String,
    shouldShowAddStory: Boolean = false
) {
    ConstraintLayout(
        modifier = Modifier
            .padding(6.dp, 6.dp)
    ) {
        val (tvName, ivAvatar, vSeenStatus, ivAdd) = createRefs()

        Box(
            modifier = Modifier
                .size(86.dp)
                .border(
                    width = if (shouldShowAddStory) (-1).dp else (2.5).dp,
                    color = Color.Red,
                    shape = CircleShape
                )
                .padding(6.dp)
                .constrainAs(vSeenStatus) {}
        )

        Image(
            painter = painterResource(id = R.drawable.img_profile),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(ivAvatar) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints

                    start.linkTo(vSeenStatus.start)
                    end.linkTo(vSeenStatus.end)
                    bottom.linkTo(vSeenStatus.bottom)
                    top.linkTo(vSeenStatus.top)
                }
                .padding(6.dp)
                .clip(CircleShape)
        )

        if (shouldShowAddStory) {
            Image(
                painter = painterResource(id = R.drawable.add_story_badge),
                contentDescription = null,
                modifier = Modifier
                    .size(27.dp)
                    .constrainAs(ivAdd) {
                        end.linkTo(ivAvatar.end, 6.dp)
                        bottom.linkTo(ivAvatar.bottom, 6.dp)
                    }
            )
        }

        Text(
            text = if (shouldShowAddStory) stringResource(id = R.string.your_story) else item,
            fontSize = 13.sp,
            maxLines = 1,
            fontWeight = FontWeight(450),
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.constrainAs(tvName) {
                width = Dimension.fillToConstraints

                start.linkTo(vSeenStatus.start)
                end.linkTo(vSeenStatus.end)
                top.linkTo(vSeenStatus.bottom)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun StoryPreview() {
    Story("lazydevv")
}