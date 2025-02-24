package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS.ui.theme.VeilTheme

//@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home_Screen(navController: NavController) {


    Scaffold(
        topBar = {
            // タイトルとアイコン表示
            TopBar_Screen(navController)
        },
        bottomBar = {
            // 下のナビゲーションバー
            BottomNavBar(navController)

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            // タブ
            TabRow(selectedTabIndex = 0) {
                Tab(
                    selected = true,
                    onClick = { },
                    text = { Text("Hot") }
                )
                Tab(
                    selected = false,
                    onClick = { },
                    text = { Text("新着順") }
                )
                Tab(
                    selected = false,
                    onClick = { },
                    text = { Text("フォロー中") }
                )
            }

            // 検索バー
            TextField(
                value = "",
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("検索") },
                leadingIcon = { Icon(Icons.Default.Search, "検索") }
            )

            // AUTO再生ボタン
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { /* ここに処理を記述 */ }) {
                    Icon(Icons.Default.PlayArrow, contentDescription = "再生")
                }
                Text("AUTO", fontWeight = FontWeight.Bold)
            }


            // 投稿リスト
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),  // 画面横との間隔
                verticalArrangement = Arrangement.spacedBy(8.dp) //次の項目との間隔
            ) {
                items(10) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(15.dp) // カードの縦の長さ
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(15.dp),
                                verticalAlignment = Alignment.CenterVertically  //高さの中央
                            ) {
                                // 音声アイコン
                                Surface(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clip(CircleShape),
                                    color = MaterialTheme.colorScheme.secondaryContainer
                                ) {
                                    Icon(
                                        Icons.Default.Mic,
                                        contentDescription = "音声",
                                        modifier = Modifier.padding(12.dp)
                                    )
                                }

                                Column {
                                    Text(
                                        "user name",
                                        fontWeight = FontWeight.ExtraBold,  //テキストの太さ
                                        fontSize = 24.sp
                                    )
                                    Text(
                                        "# 海",
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontSize = 20.sp
                                    )
                                }
                            }


                        }
                        // アクションボタン
                        Row(
                            modifier = Modifier
                                .padding(0.dp) // カードの縦の長さ
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
//                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = { /* いいね処理 */ },
                                modifier = Modifier.padding(0.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = "いいね",
                                )
                            }
                            IconButton(
                                onClick = { /* ブックマーク処理 */ },
                                modifier = Modifier.padding(0.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.BookmarkBorder,
                                    contentDescription = "ブックマーク",
                                )
                            }
                            IconButton(
                                onClick = { /* シェア処理 */ },
                                modifier = Modifier.padding(0.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Share,
                                    contentDescription = "シェア",
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// Hotの表示
@Composable
fun Hot_SoundScreen(){

//    items(10) {
//        Card(
//            modifier = Modifier.fillMaxWidth(),
//        ) {
//            Row(
//                modifier = Modifier
//                    .padding(15.dp) // カードの縦の長さ
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Row(
//                    horizontalArrangement = Arrangement.spacedBy(15.dp),
//                    verticalAlignment = Alignment.CenterVertically  //高さの中央
//                ) {
//                    // 音声アイコン
//                    Surface(
//                        modifier = Modifier
//                            .size(48.dp)
//                            .clip(CircleShape),
//                        color = MaterialTheme.colorScheme.secondaryContainer
//                    ) {
//                        Icon(
//                            Icons.Default.Mic,
//                            contentDescription = "音声",
//                            modifier = Modifier.padding(12.dp)
//                        )
//                    }
//
//                    Column {
//                        Text(
//                            "user name",
//                            fontWeight = FontWeight.ExtraBold,  //テキストの太さ
//                            fontSize = 24.sp
//                        )
//                        Text(
//                            "# 海",
//                            color = MaterialTheme.colorScheme.onSurfaceVariant,
//                            fontSize = 20.sp
//                        )
//                    }
//                }
//
//
//            }
//            // アクションボタン
//            Row(
//                modifier = Modifier
//                    .padding(0.dp) // カードの縦の長さ
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.End,
////                            verticalAlignment = Alignment.CenterVertically
//            ) {
//                IconButton(
//                    onClick = { /* いいね処理 */ },
//                    modifier = Modifier.padding(0.dp)
//                ) {
//                    Icon(
//                        imageVector = Icons.Filled.Favorite,
//                        contentDescription = "いいね",
//                    )
//                }
//                IconButton(
//                    onClick = { /* ブックマーク処理 */ },
//                    modifier = Modifier.padding(0.dp)
//                ) {
//                    Icon(
//                        imageVector = Icons.Filled.BookmarkBorder,
//                        contentDescription = "ブックマーク",
//                    )
//                }
//                IconButton(
//                    onClick = { /* シェア処理 */ },
//                    modifier = Modifier.padding(0.dp)
//                ) {
//                    Icon(
//                        imageVector = Icons.Filled.Share,
//                        contentDescription = "シェア",
//                    )
//                }
//            }
//        }
//    }

}

// 新着順の表示
@Composable
fun New_SoundScreen(){

}

// フォロー中の表示
@Composable
fun Follow_SoundScreen(){

}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    val navController = NavController(LocalContext.current)
    VeilTheme {
        Home_Screen(navController)
    }
}