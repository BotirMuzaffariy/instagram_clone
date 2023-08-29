package uz.lazydevv.instagramclone.ui.global.bottomnav.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.lazydevv.instagramclone.R

@Composable
fun MediaItemSquare(
    image: Int?,
    modifier: Modifier = Modifier,
    isShowVideoIcon: Boolean = true
) {
    Box(
        modifier = modifier.aspectRatio(1f / 1f)
    ) {
        if (image != null) {
            Image(
                painter = painterResource(id = image),
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
}

@Preview(showBackground = true)
@Composable
private fun SquareMediaItemPreview() {
    MediaItemSquare(image = R.drawable.sample_img1)
}