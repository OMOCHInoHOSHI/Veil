package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import android.app.Activity
import android.graphics.Color.rgb
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


// 通知画面
//@Preview(showBackground = true)
@Composable
fun SoundPost_Screen(navController: NavController){
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

                // 下部分
                HorizontalDividerExample()

            }


        }


    }
}


@Preview(showBackground = true)
@Composable
fun SoundPost_Screen() {
    // Use LocalConfiguration to get the screen dimensions reliably
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    // スマホの横幅を取得
    val screenWidth = configuration.screenWidthDp.dp

    // どの円が選択されたか
    var selectedCircle by remember { mutableStateOf("") }

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

                // 直線部分
                HorizontalDividerExample()

                // 投稿データS---------------------------------------------------------------

                //　イメージカラー列S----------------------------------------------------

                Box(
                    modifier = Modifier
                        .width(screenWidth * 0.9f)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "イメージカラー",
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(top = 16.dp)
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(15.dp)
                    ) {

                        // すでに選択なら空白、それ以外なら対応した色文字列
                        ToggleCircle(
                            fillColor = Color.Red,
                            borderColor = ColerSelect(),
                            selected = selectedCircle == "Red",
                            onClick = {
                                selectedCircle = if (selectedCircle == "Red") "" else "Red"
                            }
                        )

                        ToggleCircle(
                            fillColor = Color.Blue,
                            borderColor = ColerSelect(),
                            selected = selectedCircle == "Blue",
                            onClick = {
                                selectedCircle = if (selectedCircle == "Blue") "" else "Blue"
                            }
                        )

                        ToggleCircle(
                            fillColor = Color.Green,
                            borderColor = ColerSelect(),
                            selected = selectedCircle == "Green",
                            onClick = {
                                selectedCircle = if (selectedCircle == "Green") "" else "Green"
                            }
                        )

                        ToggleCircle(
                            fillColor = Color(0xFFFF47D3),
                            borderColor = ColerSelect(),
                            selected = selectedCircle == "Pink",
                            onClick = {
                                selectedCircle = if (selectedCircle == "Pink") "" else "Pink"
                            }
                        )
                    }
                }

                println("Selected circle: $selectedCircle")
                //　イメージカラー列E----------------------------------------------------


            }


        }
    }
}

// 直線を描画S--------------------------------------------------------
@Composable
fun HorizontalDividerExample() {

    // スマホの横幅を取得
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    // ダークテーマの場合は白、それ以外の場合は黒
    val dividerColor = ColerSelect()

    Column(
        modifier = Modifier.width(screenWidth * 0.9f),
        verticalArrangement = Arrangement.spacedBy(0.dp),
    ) {
        // Uncomment additional content if needed
        // Text("First item in list")

        HorizontalDivider(thickness = 5.dp, color = dividerColor)

        // Text("Second item in list")
    }
}
// 直線を描画E--------------------------------------------------------

// 円を描画S---------------------------------------------------------
@Composable
fun Circle_Draw(color: Color){

    Box(
        modifier = Modifier
            .size(24.dp) // 円のサイズを設定
            .background(
                color = color, // ここで円の色を設定
                shape = CircleShape // 形状を円形に
            )
    )
}
// 円を描画E---------------------------------------------------------

// 選択で枠付き円を描画S---------------------------------------------------------
@Composable
fun ToggleCircle(
    fillColor: Color,
    borderColor: Color,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .background(color = fillColor, shape = CircleShape)
            .clickable(onClick = onClick)
            // 選択で枠
            .then(
                if (selected) {
                    Modifier.border(width = 4.dp, color = borderColor, shape = CircleShape)
                } else Modifier
            )
    )
}
// 選択で枠付き円を描画S---------------------------------------------------------

