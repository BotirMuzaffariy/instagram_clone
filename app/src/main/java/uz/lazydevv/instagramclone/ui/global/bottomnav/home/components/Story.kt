package uz.lazydevv.instagramclone.ui.global.bottomnav.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import uz.lazydevv.instagramclone.models.StoryM
import uz.lazydevv.instagramclone.utils.MockData

@Composable
fun Story(story: StoryM) {
    ConstraintLayout(
        modifier = Modifier
            .padding(6.dp)
    ) {
        val (tvName, ivAvatar, ivAdd) = createRefs()

        StoryAvatar(
            modifier = Modifier.constrainAs(ivAvatar) {},
            avatarImg = story.user.avatarImg,
            avatarSize = StoryAvatarSize.LARGE,
            shouldShowStoryCircle = !story.isEmpty,
            isSeen = story.isSeen
        )

        if (story.isEmpty) {
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
            text = if (story.isEmpty) stringResource(id = R.string.your_story) else story.user.username,
            fontSize = 13.sp,
            maxLines = 1,
            fontWeight = FontWeight(450),
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.constrainAs(tvName) {
                width = Dimension.fillToConstraints

                start.linkTo(ivAvatar.start)
                end.linkTo(ivAvatar.end)
                top.linkTo(ivAvatar.bottom)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun StoryPreview() {
    Story(MockData.stories[0])
}