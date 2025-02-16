package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS.ui.theme.Pink40
import io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS.ui.theme.Purple40
import io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS.ui.theme.PurpleGrey40
import io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS.ui.theme.VeilTheme


// 通知画面
//@Preview(showBackground = true)
@Composable
fun NoticeScreen(navController: NavController){
    Scaffold(
        bottomBar = {BottomNavBar(navController)}


    ){innerPadding ->
        Box(modifier = Modifier.padding(innerPadding))
        Box(){
            Text("通知")
        }


    }


}


@Preview(showBackground = true)
@Composable
fun NoticeScreen() {
    // Use LocalConfiguration to get the screen dimensions reliably
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Scaffold(
        bottomBar = {}
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // マイクアイコンと再生ボタンが重ねて表示されるコンテナーS---------
                Box(
                    modifier = Modifier.size(400.dp)
                ) {
                    // マイクのアイコンを背景にしてコンテナを埋める
                    Icon(
                        imageVector = Icons.Filled.Mic,
                        contentDescription = "Microphone Icon",
                        modifier = Modifier.fillMaxSize()
                    )
                    // 再生ボタンを右下に重ねる
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = "Play Button",
                            modifier = Modifier.size(80.dp)
                        )
                    }
                }
                // マイクアイコンと再生ボタンが重ねて表示されるコンテナーE---------



            }


        }


    }
}