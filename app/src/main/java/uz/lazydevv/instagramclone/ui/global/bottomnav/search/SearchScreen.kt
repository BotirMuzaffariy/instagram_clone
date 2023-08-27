package uz.lazydevv.instagramclone.ui.global.bottomnav.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import uz.lazydevv.instagramclone.R
import uz.lazydevv.instagramclone.models.MediaM
import uz.lazydevv.instagramclone.ui.theme.Colors
import uz.lazydevv.instagramclone.utils.MockData
import kotlin.random.Random

private val contentSpacing = 2.dp

@Composable
fun SearchScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))

            CustomSearchBar()

            Spacer(modifier = Modifier.height(10.dp))
        }

        itemsIndexed(MockData.searchMediaItems) { index, mediaItems ->
            SearchMediaItem(
                items = mediaItems,
                isLongItemInRight = index % 2 == 0
            )

            Spacer(modifier = Modifier.height(contentSpacing))
        }
    }
}

@Composable
private fun CustomSearchBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp, 0.dp)
            .background(Colors.bgSearch, RoundedCornerShape(10.dp))
            .padding(12.dp, 8.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .padding(4.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = stringResource(id = R.string.search),
            color = Colors.onBgSearch,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun SearchMediaItem(
    items: List<MediaM>,
    isLongItemInRight: Boolean = true
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val (content1, content2, content3, content4, longContent) = createRefs()

        SquareMediaItem(
            image = items.getOrNull(0)?.postImg,
            isShowVideoIcon = Random.nextBoolean(),
            modifier = Modifier.constrainAs(content1) {
                width = Dimension.fillToConstraints

                top.linkTo(parent.top)
                end.linkTo(content2.start)

                if (isLongItemInRight) {
                    start.linkTo(parent.start)
                } else {
                    start.linkTo(longContent.end, contentSpacing)
                }
            }
        )

        SquareMediaItem(
            image = items.getOrNull(1)?.postImg,
            isShowVideoIcon = Random.nextBoolean(),
            modifier = Modifier.constrainAs(content2) {
                width = Dimension.fillToConstraints

                top.linkTo(content1.top)
                start.linkTo(content1.end, contentSpacing)

                if (isLongItemInRight) {
                    end.linkTo(longContent.start, contentSpacing)
                } else {
                    end.linkTo(parent.end)
                }
            }
        )

        SquareMediaItem(
            image = items.getOrNull(2)?.postImg,
            isShowVideoIcon = Random.nextBoolean(),
            modifier = Modifier.constrainAs(content3) {
                width = Dimension.fillToConstraints

                start.linkTo(content1.start)
                end.linkTo(content1.end)
                top.linkTo(content1.bottom, contentSpacing)
            }
        )

        SquareMediaItem(
            image = items.getOrNull(3)?.postImg,
            isShowVideoIcon = Random.nextBoolean(),
            modifier = Modifier.constrainAs(content4) {
                width = Dimension.fillToConstraints

                start.linkTo(content2.start)
                top.linkTo(content3.top)
                end.linkTo(content2.end)
            }
        )

        SimpleMediaItem(
            image = items.getOrNull(4)?.postImg,
            modifier = Modifier.constrainAs(longContent) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints

                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)

                if (isLongItemInRight) {
                    start.linkTo(content2.end)
                    end.linkTo(parent.end)
                } else {
                    start.linkTo(parent.start)
                    end.linkTo(content1.start)
                }
            }
        )
    }
}

@Composable
private fun SquareMediaItem(
    modifier: Modifier,
    image: Int?,
    isShowVideoIcon: Boolean = true
) {
    Box(
        modifier = modifier.aspectRatio(1f / 1f)
    ) {
        Image(
            painter = painterResource(id = image ?: R.drawable.sample_img1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        if (isShowVideoIcon) {
            Image(
                painter = painterResource(id = R.drawable.ic_reels_fill_white_gradient),
                contentDescription = null,
                modifier = Modifier
                    .padding(2.dp)
                    .size(32.dp)
                    .align(Alignment.TopEnd)
            )
        }
    }
}

@Composable
private fun SimpleMediaItem(
    modifier: Modifier,
    image: Int?,
    isShowVideoIcon: Boolean = true
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = image ?: R.drawable.sample_img1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        if (isShowVideoIcon) {
            Image(
                painter = painterResource(id = R.drawable.ic_reels_fill_white_gradient),
                contentDescription = null,
                modifier = Modifier
                    .padding(2.dp)
                    .size(32.dp)
                    .align(Alignment.TopEnd)
            )
        }
    }
}

@Preview(showSystemUi = true, device = "id:pixel_3a")
@Composable
private fun SearchScreenPreview() {
    SearchScreen()
}