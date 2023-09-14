package uz.lazydevv.instagramclone.ui.global.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.lazydevv.instagramclone.R
import uz.lazydevv.instagramclone.ui.theme.Colors

@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
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

@Preview(showBackground = true)
@Composable
private fun CustomSearchBarPreview() {
    CustomSearchBar()
}