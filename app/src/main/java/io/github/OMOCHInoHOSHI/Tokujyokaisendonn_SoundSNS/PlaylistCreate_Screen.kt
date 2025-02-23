package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// プレイリスト画面
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistCreate_Screen(navController: NavController){
    // アイコンの色を管理する状態
    var isPublic by remember { mutableStateOf(true) }

    // 大きさを画面サイズに合わせて調整
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val fontScale = screenWidth / 360.dp

    // 入力文字
    var playlistName by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF98D98E))
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // ヘッダー
            TopAppBar(
                title = { },
                navigationIcon = {
                    // 戻るボタン
                    IconButton(onClick = {

                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )

            // 上半分: イメージ画像の選択
            Column(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 画像の選択ボタン
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Add",
                            tint = Color.White,
                            modifier = Modifier
                                .size(100.dp),  // アイコンの大きさを設定
                        )
                    }
                }

                // Title
                Text(
                    "画像選択",
                    modifier = Modifier.offset(y = (-60).dp),  // 位置を少し上にずらす
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            // 下半分: その他編集
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                color = Color(0xFFFDEFF2)
            ) {
                Column {
                    // 空白を追加
                    Spacer(modifier = Modifier.height(20.dp))

                    // プレイリスト名
                    Text(
                        text = "プレイリスト名",
                        color = Color.Gray,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )

                    // Textフィールド
                    TextField(
                        value = playlistName,
                        onValueChange = { playlistName = it },
                        modifier = Modifier.padding(8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color(0xFFFFFFFF),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        // 入力文字の設定
                        textStyle = TextStyle(
                            fontSize = (14 * fontScale).sp,
                            color = Color.DarkGray
                        )
                    )

                    // 空白を追加
                    Spacer(modifier = Modifier.height(60.dp))

                    // 公開設定
                    Text(
                        text = "公開設定",
                        color = Color.Gray,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )

                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // 公開アイコン
                        IconButton(onClick = { if (!isPublic) isPublic = true }) {
                            Icon(
                                imageVector = Icons.Default.Visibility,
                                contentDescription = "Public",
                                tint = if (isPublic) Color.DarkGray else Color.LightGray
                            )
                        }
                        // 非公開アイコン
                        IconButton(onClick = { if (isPublic) isPublic = false }) {
                            Icon(
                                imageVector = Icons.Default.VisibilityOff,
                                contentDescription = "Private",
                                tint = if (isPublic) Color.LightGray else Color.DarkGray
                            )
                        }
                    }

                    // 作成ボタン
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Button(
                            onClick = { /* 作成ボタンの処理 */ },
                            modifier = Modifier
                                .padding(16.dp)
                                .size(100.dp, 50.dp)
                        ) {
                            Text("作成")
                        }
                    }
                }
            }

            // ナビゲーションバー
            BottomNavBar(navController)
        }
    }
}