package uz.lazydevv.instagramclone.ui.global.components

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
fun UsernameActionBar(
    modifier: Modifier = Modifier,
    withBackBtn: Boolean = false,
    btn1Icon: Int,
    btn2Icon: Int,
    onBtnBackClick: () -> Unit = {},
    onBtn1Click: () -> Unit = {},
    onBtn2Click: () -> Unit = {}
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        val (backBtn, tvUsername, iv0, ivCreate, ivOptions) = createRefs()

        if (withBackBtn) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clickableWithoutRipple { onBtnBackClick() }
                    .constrainAs(backBtn) {
                        start.linkTo(parent.start, 14.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }

        Text(
            text = "lazydevv",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.constrainAs(tvUsername) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)

                if (withBackBtn) {
                    start.linkTo(backBtn.end, 24.dp)
                } else {
                    start.linkTo(parent.start, 16.dp)
                }
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
            painter = painterResource(id = btn1Icon),
            contentDescription = null,
            modifier = Modifier
                .clickableWithoutRipple { onBtn1Click() }
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
            painter = painterResource(id = btn2Icon),
            contentDescription = null,
            modifier = Modifier
                .clickableWithoutRipple { onBtn2Click() }
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

@Preview(showBackground = true)
@Composable
private fun UsernameActionBarPreview() {
    UsernameActionBar(
        withBackBtn = true,
        btn1Icon = R.drawable.ic_add_box,
        btn2Icon = R.drawable.ic_menu
    )
}