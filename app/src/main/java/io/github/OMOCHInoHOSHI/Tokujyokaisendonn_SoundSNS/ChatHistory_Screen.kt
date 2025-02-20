package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// 投稿情報(仮)
data class Post(
    val id: Int,
    val username: String,
    val userid: String,
    val date: LocalDate, // LocalDate型に変更
    val content: String,
    val avatarColor: Color
)

// チャット履歴画面
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatHistory_Screen(navController: NavController) {
    // メッセージ作成ボタンのフラグ
    var ShowFlag by remember { mutableStateOf(false) }

    // 最新メッセージの日付
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日")

    // 投稿情報(仮)
    val posts = remember {
        mutableStateOf(listOf(
            Post(
                id = 1,
                username = "おもちの星(あおい)",
                userid = "@OMOTInoHOSHI",
                date = LocalDate.parse("2022年04月05日", dateFormatter),
                content = "メッセージが多いと...に変換されますよ～～～～～～～～～～",
                avatarColor = Color(0xFF007b43) // 色を設定
            ),
            Post(
                id = 2,
                username = "中村 蒼",
                userid = "@Aoi_Nakamura",
                date = LocalDate.parse("2025年02月14日", dateFormatter),
                content = "スクロールの実験のように適当に作ってます。",
                avatarColor = Color(0xFFa0d8ef) // 色を設定
            ),
            Post(
                id = 3,
                username = "レッド",
                userid = "@BS13-X01",
                date = LocalDate.parse("2011年12月08日", dateFormatter),
                content = "射手",
                avatarColor = Color(0xFFe83929) // 色を設定
            ),
            Post(
                id = 4,
                username = "ブルー",
                userid = "@BS10-X06",
                date = LocalDate.parse("2021年11月05日", dateFormatter),
                content = "蠍",
                avatarColor = Color(0xFF2ca9e1) // 色を設定
            ),
            Post(
                id = 5,
                username = "ホワイト",
                userid = "@BS12-X04",
                date = LocalDate.parse("2022年08月08日", dateFormatter),
                content = "獅子",
                avatarColor = Color(0xFFffffff) // 色を設定
            ),
            Post(
                id = 6,
                username = "グリーン",
                userid = "@BS10-X03",
                date = LocalDate.parse("2021年07月06日", dateFormatter),
                content = "蟹",
                avatarColor = Color(0xFF98d98e) // 色を設定
            ),
            Post(
                id = 7,
                username = "パープル",
                userid = "@BS12-X02",
                date = LocalDate.parse("2021年01月06日", dateFormatter),
                content = "山羊",
                avatarColor = Color(0xFF65318e) // 色を設定
            ),
            Post(
                id = 8,
                username = "イエロー",
                userid = "@BS11-X05",
                date = LocalDate.parse("2021年06月07日", dateFormatter),
                content = "双子",
                avatarColor = Color(0xFFf5e56b) // 色を設定
            ),
            Post(
                id = 9,
                username = "疲れた",
                userid = "@jiminiooi",
                date = LocalDate.parse("2023年02月16日", dateFormatter),
                content = "投稿(仮)作るの疲れた！！！！！",
                avatarColor = Color(0xFF9ea1a3) // 色を設定
            ),
            // 他の投稿を追加
        ).sortedByDescending { it.date }) // 日付でソート
    }

    // 大きさを画面サイズに合わせて調整
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val fontScale = screenWidth / 360.dp // 基準となる画面幅を360dpとする

    var searchText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF121212))
            ) {
                // ヘッダーの上に空白を追加
                Spacer(modifier = Modifier.height(25.dp))

                // ヘッダー
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(Color(0xFF121212))
                ) {
                    Text(
                        text = "Veil",
                        color = Color.White,
                        fontSize = (20 * fontScale).sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Center)
                    )
                    ///* 仮のボタン(データーベース内の画像を使えるようになったら消す)
                    Button(
                        onClick = { /* Picture機能 */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE91E63)
                        ),
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 16.dp)
                            .height(40.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp)
                    ) {
                        Text("Picture", fontSize = (14 * fontScale).sp)
                    }
                    //*/

                    /* 画像アイコン(データーベース内の画像を使えるようになったら使う)
                    IconButton(
                        onClick = { /* Picture機能 */ },
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 16.dp)
                            .size(40.dp) // ボタンのサイズを調整
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.picture_icon),
                            contentDescription = "Picture",
                            modifier = Modifier
                                .size(40.dp) // 画像のサイズを調整
                                .clip(CircleShape) // 画像を丸形にクリップ
                        )
                    }
                    */
                }


                // 検索バー
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
//                    // 大きさを大きく、少し左にボタンの位置をVEilと同じに変更
//                    IconButton(onClick = { /* 設定画面へ */ }) {
//                        Icon(
//                            Icons.Default.Settings,
//                            contentDescription = "Settings",
//                            tint = Color.White
//                        )
//                    }
                    TextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp) // 高さを調整
                            .clip(RoundedCornerShape(12.dp)), // 四隅を丸くする
                        placeholder = {
                            // 入力値がない時
                            if (searchText.isEmpty()) {
                                Text(
                                    "メッセージの検索",
                                    color = Color.LightGray,
                                    fontSize = (11 * fontScale).sp,
                                    modifier = Modifier.offset(y = (-2).dp) // y軸方向に-2dp移動
                                )                            }
                        },
                        // テキストフィールドのアイコン
                        leadingIcon = {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = "Search",
                                tint = Color.Gray
                            )
                        },
                        trailingIcon = {
                            // バツアイコン
                            if (searchText.isNotEmpty()) {
                                IconButton(onClick = { searchText = "" }) {
                                    Icon(
                                        Icons.Default.Close,
                                        contentDescription = "Clear",
                                        tint = Color.LightGray
                                    )
                                }
                            }
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color(0xFF242424),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        // 入力文字の設定
                        textStyle = TextStyle(
                            fontSize = (11 * fontScale).sp,
                            color = Color.LightGray
                        ),
                        singleLine = true       // 文字列を１行に収める設定
                    )
//                    // Veilに合わせる
//                    Button(
//                        onClick = { /* Picture機能 */ },
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = Color(0xFFE91E63)
//                        ),
//                        modifier = Modifier
//                            .padding(start = 8.dp)
//                            .height(40.dp),
//                        contentPadding = PaddingValues(horizontal = 12.dp)
//                    ) {
//                        Text("Picture", fontSize = (14 * fontScale).sp)
//                    }
                }
            }
        },
        // ナビゲーションバー
        bottomBar = {
            BottomNavBar(navController)
//            NavigationBar(
//                containerColor = Color(0xFF121212),
//                modifier = Modifier.background(Color(0xFF121212))
//            ) {
//                NavigationBarItem(
//                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
//                    selected = true,
//                    onClick = { },
//                    colors = NavigationBarItemDefaults.colors(
//                        selectedIconColor = Color.White,
//                        unselectedIconColor = Color.Gray
//                    )
//                )
//                NavigationBarItem(
//                    icon = { Icon(Icons.Default.Home, contentDescription = "Mic") },
//                    selected = false,
//                    onClick = { },
//                    colors = NavigationBarItemDefaults.colors(
//                        selectedIconColor = Color.White,
//                        unselectedIconColor = Color.Gray
//                    )
//                )
//                NavigationBarItem(
//                    icon = { Icon(Icons.Default.Notifications, contentDescription = "Notifications") },
//                    selected = false,
//                    onClick = { },
//                    colors = NavigationBarItemDefaults.colors(
//                        selectedIconColor = Color.White,
//                        unselectedIconColor = Color.Gray
//                    )
//                )
//            }
        },
        // メッセージ作成ボタン
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* メッセージ作成 */
//                    ShowFlag = true
                    navController.navigate("MessageSelect_Screen")
                          },
                containerColor = Color(0xFF2196F3),
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Chat, contentDescription = "New Message")
            }
        }
    )
    // 調べる
    { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF121212)),
            contentPadding = PaddingValues(vertical = 8.dp) // 投稿同士の空白を狭くする
        ) {
            items(posts.value) { post ->
                PostItem(post, screenWidth, fontScale, navController)
            }
        }
    }

    // メッセージ作成ボタンの判定
    if (ShowFlag == true) {
        MessageDialog(onDismiss = { ShowFlag = false })
    }
}

// ユーザー一覧の表示
@Composable
fun PostItem(post: Post, screenWidth: androidx.compose.ui.unit.Dp, fontScale: Float, navController: NavController) {
    // 最新メッセージの日付フォーマット
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日")

    // ユーザー一覧の作成
    Row(
        modifier = Modifier
            .width(screenWidth)
            .padding(horizontal = 16.dp, vertical = 4.dp) // 上下のパディングを狭くする
            .clickable {
                // 投稿をタッチしたときの処理
                navController.navigate("MessageChat_Screen")
            },
    ) {
        // アイコン
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(post.avatarColor, CircleShape) // 色を設定
        )

        // 投稿内容
        Column(
            modifier = Modifier
                .padding(start = 12.dp)
                .width(screenWidth - 84.dp) // アバターとパディングの幅を引く
        ) {
            // ユーザー名
            Text(
                text = post.username + " " + post.userid,
                fontSize = (16 * fontScale).sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis // 残りを「...」で表示
            )
            // 最新メッセージ
            Text(
                text = post.content,
                fontSize = (12 * fontScale).sp,
                color = Color.LightGray,
                modifier = Modifier.padding(top = 4.dp),
                maxLines = 1,   // 1行に制限
                overflow = TextOverflow.Ellipsis    // 残りを「...」で表示
            )
            // 最新メッセージの日付
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = post.date.format(dateFormatter), // LocalDateをStringに変換
                    fontSize = (10 * fontScale).sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }
    }
}

// メッセージ作成ボタン仮
@Composable
fun MessageDialog(onDismiss: () -> Unit) {
    Log.d("Create_Message", "ウィンドウの作成")

    Box(
        // ウィンドウ以外の背景
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x80000000)) // 半透明の背景
            .wrapContentSize(Alignment.Center)
    ) {
        Box(
            // ウィンドウ
            modifier = Modifier
                .size(300.dp, 200.dp)
                .background(Color.White, RoundedCornerShape(12.dp))
        ) {
            // ✕ボタン
            IconButton(
                onClick = onDismiss,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            ) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.Red
                )
            }
            // ウィンドウの内容をここに追加
        }
    }
}
