package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 音声(仮)
data class Sound(
    val title: String,
    val user: String,
    val hash: String,
    val PostColor: Color,
    val UserIcon: Color
)

// プレイリスト画面
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Playlist_Screen(navController: NavController){
    // 音声リスト(仮)
    val sounds = remember {
        listOf(
            Sound("Sound1", "N_A", "#街中, #帰り道", Color(0xFFA0D8EF), Color(0xFF65318E)),
            Sound("Sound2", "リオ", "#海, #青春", Color(0xFFafeeee), Color(0xFF98D98E)),
            Sound("Sound3", "リオ", "#山", Color(0xFF38b48b), Color(0xFF98D98E)),
            Sound("Sound4", "N_A", "#仕事", Color(0xFF5b7e91), Color(0xFF65318E)),
            Sound("Sound5", "N_A", "#睡眠, #安らぎ, #休憩", Color(0xFFf2f2b0), Color(0xFF65318E)),
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
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // 上半分: ヘッダーとタイトル
            Box(
                modifier = Modifier
                    .height(300.dp)  // 高さを短く設定
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.kari_image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0x80000000)),  // 半透明の黒背景を追加してテキストを見やすくする
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // ヘッダー
                    TopAppBar(
                        title = { },
                        navigationIcon = {
                            // 戻るボタン
                            IconButton(onClick = {
                                navController.navigate("Profile_Screen")
                            }) {
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

                    // タイトル
                    Text(
                        "お気に入り",
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
            }

            // 下半分: ユーザー情報と音声リスト
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                color = Color(0xFFFDEFF2)
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
                                containerColor = Color(0xFFFFFFFF)
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
                                            .background(sound.UserIcon)
                                    )
                                    Column {
                                        Text(
                                            sound.user,
                                            fontWeight = FontWeight.Medium
                                        )
                                        Text(
                                            sound.hash,
                                            fontSize = 14.sp
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
                                            tint = Color.Gray
                                        )
                                    }
                                    // 共有ボタン
                                    IconButton(onClick = { }) {
                                        Icon(
                                            Icons.Default.Share,
                                            contentDescription = "Share",
                                            tint = Color.Gray
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
                        .background(Color(0xFFFFFFFF))
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
                                    .background(sound.PostColor)
                            )
                            Column {
                                Text(
                                    sound.hash,
                                    color = Color.DarkGray,
                                    fontWeight = FontWeight.Medium
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
                                    tint = Color.Gray
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
                                    tint = Color.Gray
                                )
                            }
                            // バツボタン
                            IconButton(onClick = { selectedSound = null; isPlaying = false }) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = "Close",
                                    tint = Color.Gray
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
                    .background(sound.PostColor)
            )
            Column {
                Text(
                    sound.hash,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Medium
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
                    tint = Color.Gray
                )
            }
            // その他ボタン
            IconButton(onClick = { }) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "More",
                    tint = Color.Gray
                )
            }
        }
    }
}