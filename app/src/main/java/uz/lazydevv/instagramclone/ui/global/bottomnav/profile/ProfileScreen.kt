package uz.lazydevv.instagramclone.ui.global.bottomnav.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import uz.lazydevv.instagramclone.R
import uz.lazydevv.instagramclone.extensions.clickableWithoutRipple

@Composable
fun ProfileScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        ProfileActionBar()
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

@Preview(showSystemUi = true, device = "id:pixel_3a")
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen()
}