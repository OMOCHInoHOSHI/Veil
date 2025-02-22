package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
    val artist: String,
    val color: Color
)

// プレイリスト画面
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Playlist_Screen(navController: NavController){
    // 音声リスト(仮)
    val Sounds = remember {
        listOf(
            Sound("Sound1", "aaa", Color(0xFFa0d8ef)),
            Sound("Sound2", "bbb", Color(0xFFa0d8ef)),
            Sound("Sound3", "ccc", Color(0xFFa0d8ef)),
            Sound("Sound4", "ddd", Color(0xFFa0d8ef)),
            Sound("Sound5", "eee", Color(0xFFa0d8ef))
        )
    }

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
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More", tint = Color.White)
                    }
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
                    Card(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
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
                                        .background(Color.Gray)
                                )
                                Column {
                                    Text(
                                        "ユーザー名",
                                        fontWeight = FontWeight.Medium
                                    )
                                    Text(
                                        "#街中",
                                        fontSize = 12.sp
                                    )
                                }
                            }

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    IconButton(onClick = { }) {
                                        Icon(
                                            Icons.Default.Favorite,
                                            contentDescription = "Like",
                                            tint = Color.White
                                        )
                                    }
                                    Text(
                                        "1.2M",
                                        color = Color.White,
                                        fontSize = 12.sp
                                    )
                                }
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

                    // 音声リスト
                    LazyColumn {
                        items(Sounds) { Sound ->
                            SoundItem(Sound)
                        }
                    }
                }
            }

            // 再生中バー
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
                                .background(Color(0xFFa0d8ef))
                        )
                        Column {
                            Text(
                                "再生中の音声タイトル",
                                color = Color.White,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                "再生中の音声ユーザー",
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // 再生ボタン
                        IconButton(onClick = { }) {
                            Icon(
                                Icons.Default.PlayArrow,
                                contentDescription = "Play",
                                tint = Color.White
                            )
                        }
                        // ループ再生
                        IconButton(onClick = { }) {
                            Icon(
                                Icons.Default.Repeat,
                                contentDescription = "Repeat",
                                tint = Color.White
                            )
                        }
                    }
                }
            }

            // ナビゲーションバー
            BottomNavBar(navController)
        }
    }
}

// SoundItem Composable は変更なし
@Composable
fun SoundItem(Sound: Sound) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
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
                    .clip(RoundedCornerShape(4.dp))
                    .background(Sound.color)
            )
            Column {
                Text(
                    Sound.title,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    Sound.artist,
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IconButton(onClick = { }) {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = "Like",
                    tint = Color.White
                )
            }
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