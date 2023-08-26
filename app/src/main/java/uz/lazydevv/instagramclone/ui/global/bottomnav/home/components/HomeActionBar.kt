package uz.lazydevv.instagramclone.ui.global.bottomnav.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import uz.lazydevv.instagramclone.R
import uz.lazydevv.instagramclone.extensions.clickableWithoutRipple

@Composable
fun HomeActionBar(
    modifier: Modifier = Modifier,
    onNewsClick: () -> Unit = {},
    onMessengerClick: () -> Unit = {}
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        val (ivLogo, iv0, ivNews, ivMessenger) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.nav_logo),
            contentDescription = null,
            modifier = Modifier.constrainAs(ivLogo) {
                width = Dimension.percent(0.28f)

                start.linkTo(parent.start, 16.dp)
                top.linkTo(parent.top, 8.dp)
                bottom.linkTo(parent.bottom)
            }
        )

        Image(
            painter = painterResource(id = R.drawable.ic_keyboard_arrow_down),
            contentDescription = null,
            modifier = Modifier
                .size(16.dp)
                .constrainAs(iv0) {
                    start.linkTo(ivLogo.end, 2.dp)
                    top.linkTo(ivLogo.top)
                    bottom.linkTo(ivLogo.bottom)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.ic_heart),
            contentDescription = null,
            modifier = Modifier
                .clickableWithoutRipple { onNewsClick() }
                .constrainAs(ivNews) {
                    height = Dimension.matchParent
                    width = Dimension.ratio("H, 1:0.8")

                    top.linkTo(ivLogo.top)
                    bottom.linkTo(ivLogo.bottom)
                    end.linkTo(ivMessenger.start)
                }
                .padding(11.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_messenger_outline),
            contentDescription = null,
            modifier = Modifier
                .clickableWithoutRipple { onMessengerClick() }
                .constrainAs(ivMessenger) {
                    height = Dimension.matchParent
                    width = Dimension.ratio("H, 1:0.8")

                    top.linkTo(ivLogo.top)
                    bottom.linkTo(ivLogo.bottom)
                    end.linkTo(parent.end, 8.dp)
                }
                .padding(11.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeActionBarPreview() {
    HomeActionBar()
}