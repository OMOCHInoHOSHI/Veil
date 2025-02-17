package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS.ui.theme.VeilTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VeilTheme {

                // ログイン成功でHome起動
//                if (LoginScreen()){
//                    DisplayNav()
//                }

                DisplayNav()

//                SoundSNSApp()
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VeilTheme {
        Greeting("Android")
    }
}


// システムカラーに合わせて白か黒を決定S----------------------------------------
@Composable
fun ColerSelect(): Color {
    // システムがダークテーマになっているかどうかを判断
    val isDarkTheme = isSystemInDarkTheme()
    // ダークテーマの場合は白、それ以外の場合は黒
    val dividerColor = if (isDarkTheme) Color.White else Color.Black

    return dividerColor
}
// システムカラーに合わせて白か黒を決定E----------------------------------------