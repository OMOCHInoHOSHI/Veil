package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

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
import java.text.SimpleDateFormat
import java.util.*

// チャットメッセージ(仮)
data class ChatMessage(
    val content: String,
    val timestamp: String,
    val isFromMe: Boolean,
    val isRead: Boolean = false
)

// メッセージチャット画面
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageChat_Screen(navController: NavController) {
    // 大きさを画面サイズに合わせて調整
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val fontScale = screenWidth / 360.dp

    var messageText by remember { mutableStateOf("") }

    // メッセージ作成ボタンのフラグ
    var ShowFlag by remember { mutableStateOf(false) }

    // メッセージ情報(仮)
    val messages = remember {
        mutableStateListOf(
            ChatMessage("↓仮のメッセージです。", "2:56", true),
            ChatMessage("次の調整は下の3つの予定です。", "2:57", true),
            ChatMessage("・前画面から引数を受け取れるように変更", "3:51", true),
            ChatMessage("・戻るボタンをタップ後のイベント追加", "3:51", true),
            ChatMessage("・スマホの大きさによってTextフィールドなどの大きさが変化するように変更", "4:01", true),
            ChatMessage("相手からの返信はこのような感じです。", "4:04", false),
            ChatMessage("↑ここまでが仮メッセージです。", "4:37", true)
        )
    }

    Scaffold(
        // フッター(的な)
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // 相手のアイコン
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color.Gray)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        // 相手のアカウント名
                        Text(
                            text = "tjop0044",
                            color = Color.White,
                            fontSize = (16 * fontScale).sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                },
                // 戻るボタン
                navigationIcon = {
                    IconButton(onClick = { /* 戻る */ ShowFlag = true }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                // ヘッダーの背景
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF121212)
                )
            )
        },
        // フッター(的な)
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF121212))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                // 入力バー
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = messageText,
                        onValueChange = { messageText = it },
                        modifier = Modifier
                            .weight(1f)
                            .heightIn(min = 50.dp, max = 200.dp) // 高さの最小値と最大値を設定
                            .clip(RoundedCornerShape(12.dp)), // 四隅を丸くする
                        // 文字が入力されていない時の表示
                        placeholder = {
                            Text(
                                "メッセージを作成",
                                color = Color.Gray,
                                fontSize = (14 * fontScale).sp
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color(0xFF242424),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(24.dp),
                        // 送信ボタン
                        trailingIcon = {
                            if (messageText.isNotEmpty()) {
                                IconButton(
                                    onClick = {
                                        // メッセージを送信
                                        val timestamp = SimpleDateFormat(
                                            "h:mm",
                                            Locale.getDefault()
                                        ).format(Date())
                                        messages.add(
                                            ChatMessage(
                                                messageText,
                                                timestamp,
                                                true
                                            )
                                        )
                                        messageText = ""
                                    }
                                ) {
                                    Icon(
                                        Icons.Default.Send,
                                        contentDescription = "Send",
                                        tint = Color(0xFF2196F3)
                                    )
                                }
                            }
                        },
                        // 入力文字の設定
                        textStyle = TextStyle(
                            fontSize = (14 * fontScale).sp,
                            color = Color.LightGray
                        ),
                        maxLines = 4 // 最大行数を設定
                    )
                }
            }
        }
    ) { paddingValues ->
        // メッセージの表示
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.Black)
                .padding(horizontal = 16.dp),
            reverseLayout = true
        ) {
            items(messages.asReversed()) { message ->
                ChatMessageItem(message, fontScale)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

    // 戻るボタンの判定
    if (ShowFlag == true) {
        back(onDismiss = { ShowFlag = false })
    }
}

@Composable
fun ChatMessageItem(message: ChatMessage, fontScale: Float) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (message.isFromMe) Alignment.End else Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = if (message.isFromMe) 16.dp else 4.dp,
                        bottomEnd = if (message.isFromMe) 4.dp else 16.dp
                    )
                )
                .background(
                    if (message.isFromMe) Color(0xFF2196F3)     // 自分のメッセージ背景
                    else Color(0xFF424242)      // 相手のメッセージ背景
                )
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            // メッセージの文字
            Text(
                text = message.content,
                color = Color.White,
                fontSize = (14 * fontScale).sp
            )
        }
        // メッセージと送信時間の空白
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (message.isFromMe) Arrangement.End else Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            // 送信時間
            Text(
                text = message.timestamp,
                color = Color.Gray,
                fontSize = (12 * fontScale).sp
            )

            // 既読、未読情報
            if (message.isFromMe && message.isRead) {
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    Icons.Default.Done,
                    contentDescription = "Read",
                    tint = Color.Gray,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        // メッセージごとの空白
        Spacer(modifier = Modifier.height(5.dp))
    }
}
