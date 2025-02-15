package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS.ui.theme
///*
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS.BottomNavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Select_chatroom(navController: NavController) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val fontScale = screenWidth / 360.dp

    var searchText by remember { mutableStateOf("") }

    val messages = remember {
        listOf(
            MessageItem("グループを作成", "", "", true, true),
            MessageItem("おもちの星", "@OMOTInoHOSHI", "", false),
            MessageItem("アノード(50Hz)", "@Anodesounmemww", "", false),
            MessageItem("リア友共", "", "5人", false),
            MessageItem("MOTOR-MAN", "@mmtaiwan0523", "", false),
            MessageItem("looser 🎮📖 👍", "@Looser90060474", "と他334人", false),
            MessageItem("tjop0044", "@tjop0044", "", false),
            MessageItem("高杉 またこ", "@wwTvL8MK82@wK89", "", false),
            MessageItem("らぁめんがたべたい", "@Kansaipenta", "", false),
            MessageItem("品川家プロジェクト", "@Sinagawake", "", false),
            MessageItem("yusei", "@M_K_L_029", "", false),
            MessageItem("パブ公式、自分", "", "1人", false)
        )
    }

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

                // 「ダイレクトメッセージ」のテキストを追加
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp) // 高さを調整
                        .background(Color(0xFF121212)),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { /* 戻る */ }) {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                        Text(
                            text = "ダイレクトメッセージ",
                            color = Color.White,
                            fontSize = (16 * fontScale).sp
                        )
                    }
                }

                // 検索バー
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
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
                                )
                            }
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
                }
            }
        },
        // ナビゲーションバー
        bottomBar = {
            BottomNavBar(navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.Black)
        ) {
            // メッセージリスト
            LazyColumn {
                items(messages) { message ->
                    MessageRow(message, fontScale)
                }
            }
        }
    }
}

@Composable
fun MessageRow(message: MessageItem, fontScale: Float) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // アバターまたはグループアイコン
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    if (message.isGroup) Color(0xFF1DA1F2)
                    else Color.Gray,
                    CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (message.isGroup) {
                Icon(
                    Icons.Default.Group,
                    contentDescription = "Group",
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = message.name,
                    color = Color.White,
                    fontSize = (15 * fontScale).sp,
                    fontWeight = FontWeight.Normal
                )
                if (message.members.isNotEmpty()) {
                    Text(
                        text = message.members,
                        color = Color.Gray,
                        fontSize = (13 * fontScale).sp,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
            if (message.handle.isNotEmpty()) {
                Text(
                    text = message.handle,
                    color = Color.Gray,
                    fontSize = (13 * fontScale).sp
                )
            }
        }
    }
}

data class MessageItem(
    val name: String,
    val handle: String,
    val members: String,
    val isGroup: Boolean,
    val isCreateGroup: Boolean = false
)
//*/