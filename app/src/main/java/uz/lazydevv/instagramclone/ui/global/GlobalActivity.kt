package uz.lazydevv.instagramclone.ui.global

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import uz.lazydevv.instagramclone.ui.theme.InstagramCloneTheme

class GlobalActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            InstagramCloneTheme {
                val navController = rememberNavController()
                GlobalNavHost(navController)
            }
        }
    }
}

@Preview(showSystemUi = true, device = "id:pixel_3a")
@Composable
private fun GreetingPreview() {
    InstagramCloneTheme {
        GlobalNavHost(rememberNavController())
    }
}