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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS.ui.theme.VeilTheme
import org.openapitools.client.apis.UserApi

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VeilTheme {

//                Message_page()
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }


                // ログインで来たか保存
                var loginflg by rememberSaveable { mutableStateOf(false) }
//                val userApi = UserApi("\"http://192.168.10.109:8088/v1\"")
//                val users = userApi

                // ログイン成功ならDisplayNavへ
                if (loginflg){
                    DisplayNav()
                }else{
                    loginflg = LoginScreen()
                }

//                DisplayNav()
//                SoundPost_Screen()
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

