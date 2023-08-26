package uz.lazydevv.instagramclone.ui.global

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import uz.lazydevv.instagramclone.ui.global.bottomnav.BottomNavScreen
import uz.lazydevv.instagramclone.ui.theme.InstagramCloneTheme

class GlobalActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            InstagramCloneTheme {
                BottomNavScreen()
            }
        }
    }
}

@Preview(showSystemUi = true, device = "id:pixel_3a")
@Composable
private fun GreetingPreview() {
    InstagramCloneTheme {
        BottomNavScreen()
    }
}