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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 音声(仮)
data class Sound(
    val title: String,
    val user: String,
    val hash: String,
    val color: Color
)

// プレイリスト画面
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Playlist_Screen(navController: NavController){
    // 音声リスト(仮)
    val sounds = remember {
        listOf(
            Sound("Sound1", "Aaa", "街中", Color(0xFFa0d8ef)),
            Sound("Sound2", "Bbb", "海", Color(0xFFa0d8ef)),
            Sound("Sound3", "Ccc", "山", Color(0xFFa0d8ef)),
            Sound("Sound4", "Ddd", "仕事", Color(0xFFa0d8ef)),
            Sound("Sound5", "Eee", "睡眠", Color(0xFFa0d8ef)),
        )
    }

    // 選択された音声
    var selectedSound by remember { mutableStateOf<Sound?>(null) }

    // 再生状況
    var isPlaying by remember { mutableStateOf(false) }

    // メニュー表示の判定
    var MenuFlag by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF98d98e))
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // ヘッダー
            TopAppBar(
                title = { },
                navigationIcon = {
                    // 戻るボタン
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                // メニューボタン
                actions = {
                    IconButton(onClick = { MenuFlag = !MenuFlag }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More", tint = Color.White)
                    }

                    // ドロップダウンメニューの表示
                    DropDownMenu(MenuFlag, onDismiss = { MenuFlag = false })
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )

            // 上半分: タイトル
            Column(
                modifier = Modifier
                    .height(200.dp)  // 高さを短く設定
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title
                Text(
                    "海音まとめ",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                // 再生ボタン
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = { isPlaying = !isPlaying },
                        modifier = Modifier.size(60.dp)  // ボタンの大きさを設定
                    ) {
                        Icon(
                            if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = if (isPlaying) "Pause" else "Play",
                            tint = Color.White,
                            modifier = Modifier.size(60.dp)  // アイコンの大きさを設定
                        )
                    }
                }
            }

            // 下半分: ユーザー情報と音声リスト
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                color = Color(0xFF1A1A1A)
            ) {
                Column {
                    // ユーザー情報
                    selectedSound?.let { sound ->
                        Card(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .height(80.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0x1AFFFFFF)
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(48.dp)
                                            .clip(CircleShape)
                                            .background(sound.color)
                                    )
                                    Column {
                                        Text(
                                            sound.user,
                                            fontWeight = FontWeight.Medium
                                        )
                                        Text(
                                            sound.hash,
                                            fontSize = 12.sp
                                        )
                                    }
                                }

                                Row(
                                    modifier = Modifier.offset(y = (-2).dp)  // 位置を少し上にずらす
                                ) {
                                    // ハートボタン
                                    IconButton(onClick = { }) {
                                        Icon(
                                            Icons.Default.Favorite,
                                            contentDescription = "Like",
                                            tint = Color.White
                                        )
                                    }
                                    // 共有ボタン
                                    IconButton(onClick = { }) {
                                        Icon(
                                            Icons.Default.Share,
                                            contentDescription = "Share",
                                            tint = Color.White
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // 音声リスト
                    LazyColumn {
                        items(sounds) { sound ->
                            SoundItem(sound) {
                                selectedSound = sound
                                isPlaying = true
                            }
                        }
                    }
                }
            }

            // 再生中バー
            selectedSound?.let { sound ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xE6301934))
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(sound.color)
                            )
                            Column {
                                Text(
                                    sound.title,
                                    color = Color.White,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    sound.user,
                                    color = Color.Gray,
                                    fontSize = 12.sp
                                )
                            }
                        }
                        // 再生ボタン
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            IconButton(onClick = { isPlaying = !isPlaying }) {
                                Icon(
                                    if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                    contentDescription = if (isPlaying) "Pause" else "Play",
                                    tint = Color.White
                                )
                            }
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // ループ再生ボタン
                            IconButton(onClick = { }) {
                                Icon(
                                    Icons.Default.Repeat,
                                    contentDescription = "Repeat",
                                    tint = Color.White
                                )
                            }
                            // バツボタン
                            IconButton(onClick = { selectedSound = null; isPlaying = false }) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = "Close",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }
            }

            // ナビゲーションバー
            BottomNavBar(navController)
        }
    }
}

// ドロップダウンメニュー
@Composable
fun DropDownMenu(showMenu: Boolean, onDismiss: () -> Unit) {
    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = onDismiss
    ) {
        // 共有
        DropdownMenuItem(
            text = { Text("プレイリスト共有") },
            onClick = {
                onDismiss()
            }
        )

        // 編集
        DropdownMenuItem(
            text = { Text("プレイリスト編集") },
            onClick = {
                onDismiss()
            }
        )

        // イメージ画像の選択
        DropdownMenuItem(
            text = { Text("イメージ画像選択") },
            onClick = {
                onDismiss()
            }
        )

        // 削除
        DropdownMenuItem(
            text = { Text("プレイリスト削除") },
            onClick = {
                onDismiss()
            }
        )
    }
}

// プレイリスト
@Composable
fun SoundItem(sound: Sound, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                onClick()
                //isPlaying = true  // 他の音声を押したときに再生ボタンに変わるようにする
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(sound.color)
            )
            Column {
                Text(
                    sound.title,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    sound.user,
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // ハートボタン
            IconButton(onClick = { }) {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = "Like",
                    tint = Color.White
                )
            }
            // その他ボタン
            IconButton(onClick = { }) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "More",
                    tint = Color.White
                )
            }
        }
    }
}