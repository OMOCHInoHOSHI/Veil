package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DataSaverOff
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SaveAlt
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS.ui.theme.VeilTheme


// 通知画面
@Composable
fun SoundPost_Screen(navController: NavController, soundView: SoundViewModel){

    val context = LocalContext.current
    // AudioRecordTest のインスタンスを生成（Activity のライフサイクル外でも利用可能なようにコンストラクタで Context を渡しています）
    val audioRecordTest = AudioRecordTest(context, soundView)

    // 再生状態を監視
//    val soundView: SoundViewModel = viewModel()
    val isPlaying by soundView.soundPlaying.collectAsState()
    var playflg by remember { mutableStateOf(false) }

    // Use LocalConfiguration to get the screen dimensions reliably
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    // スマホの横幅を取得
    val screenWidth = configuration.screenWidthDp.dp

    // どの円が選択されたか
    var selectedCircle by remember { mutableStateOf("") }

    // 保存用ハッシュタグリスト
    var tags:List<String> = remember { mutableStateListOf("") }

    Scaffold(
        bottomBar = {

            Column(
            ) {
                // 投稿ボタンの行S--------------------------------------------------------
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),  // 親の幅いっぱいにする
                    contentAlignment = Alignment.Center  // コンテンツを中央揃え
                ) {
                    Row(
                        modifier = Modifier
                            .width(screenWidth * 0.9f)  // 90%の幅にする
                            .background(Color.LightGray),
                        horizontalArrangement = Arrangement.SpaceBetween,  // アイコンを左右の端に配置
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // 左側の下書き保存アイコン
                        IconButton(onClick = { /* doSomething() for draft saving */ }) {
                            Icon(
                                imageVector = Icons.Default.DriveFileRenameOutline, // ペンマーク
                                contentDescription = "下書き保存"
                            )
                        }

                        // 右側の投稿アイコン
                        IconButton(onClick = { /* doSomething() for sending post */ }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Send,
                                contentDescription = "投稿"
                            )
                        }
                    }
                }
                // 投稿ボタンの行E--------------------------------------------------------

                BottomNavBar(navController, soundView)
            }
        }
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
                    modifier = Modifier
                        .size(300.dp)
                        .clickable{
                            if(isPlaying){
                                audioRecordTest.onPlay(false)
//                                soundView.setSoundPlaying(false)
                            }else{
                                audioRecordTest.onPlay(true)
//                                soundView.setSoundPlaying(true)
                            }
                        }
                ) {

                    Icon(
                        imageVector = if (isPlaying) Icons.Filled.Stop else Icons.Filled.PlayArrow,
                        contentDescription = if (isPlaying) "再生停止" else "再生開始",
                        modifier = Modifier.fillMaxSize()
                    )

//                    // マイクのアイコンを背景にしてコンテナを埋める
//                    Icon(
//                        imageVector = Icons.Filled.Mic,
//                        contentDescription = "Microphone Icon",
//                        modifier = Modifier.fillMaxSize()
//                    )
//                    // 再生ボタンを右下に重ねる
//                    Box(
//                        modifier = Modifier.fillMaxSize(),
//                        contentAlignment = Alignment.BottomEnd
//                    ) {
//                        Icon(
//                            imageVector = if (isPlaying) Icons.Filled.Stop else Icons.Filled.PlayArrow,
//                            contentDescription = if (isPlaying) "再生停止" else "再生開始",
//                            modifier = Modifier.size(80.dp)
//                        )
//                    }
                }
                // マイクアイコンと再生ボタンが重ねて表示されるコンテナーE---------

                // 直線部分
                HorizontalDividerExample()

                // 投稿データS---------------------------------------------------------------

                //　イメージカラー行S----------------------------------------------------

                Box(
                    modifier = Modifier
                        .width(screenWidth * 0.9f)  // 開始位置を線の下に
                        .fillMaxWidth()
                ) {

                    // イメージカラーのテキストS------------------------------------
                    Text(
                        text = "イメージカラー",
                        modifier = Modifier
                            .align(Alignment.CenterStart)  // 左寄せ
                            .padding(top = 16.dp)  // 仕切りから少し間隔をあける
                    )
                    // イメージカラーのテキストE------------------------------------

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
                            fillColor = Color(0xFF4EB0F4),
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
                //　イメージカラー行E----------------------------------------------------

                // ハッシュタグS--------------------------------------------------------------
                Box(
                    modifier = Modifier
                        .width(screenWidth * 0.9f)  // 開始位置を線の下に
                        .fillMaxWidth()
                ){

                    LazyColumn {
                        item {
                            tags = DynamicHashtagTextFields()
                            println("tags: $tags")
                        }
                    }
                }
                // ハッシュタグE--------------------------------------------------------------

                // 投稿データE---------------------------------------------------------------
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
    onClick: () -> Unit,
) {
    // 内側の円のサイズと外側の枠の幅を定義
    val innerSize: Dp = 24.dp
    val borderWidth: Dp = 4.dp
    // コンテナの全体サイズは、内側の円と枠分を含む
    val totalSize = innerSize + borderWidth * 2

    Box(
        modifier = Modifier
            .size(totalSize)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        // 選択状態なら borderColor で枠を描画し、非選択時は背景を透明にしてスペースを確保
        Box(
            modifier = Modifier
                .size(totalSize)
                .background(
                    color = if (selected) borderColor else Color.Transparent,
                    shape = CircleShape
                )
        )
        // 内側の円
        Box(
            modifier = Modifier
                .size(innerSize)
                .background(color = fillColor, shape = CircleShape)
        )
    }
}
// 選択で枠付き円を描画E---------------------------------------------------------



// ハッシュタグリストのUIを実装S-----------------------------------------------------------------------
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DynamicHashtagTextFields(): List<String> {
    // 親側でタグリストの状態を管理
    val hashtags = remember { mutableStateListOf("") }

    FlowRow(
        modifier = Modifier.padding(5.dp),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
    ) {
        hashtags.forEachIndexed { index, text ->
            // 安定したキーを指定して再利用時の状態ずれを回避
            androidx.compose.runtime.key(index) {
                androidx.compose.foundation.layout.Row(
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    DynamicHashtagTextField(
                        text = text,
                        onTextChanged = { newText ->
                            hashtags[index] = newText
                        },
                        onDelete = {
                            if (hashtags.size > 1) {
                                hashtags.removeAt(index)
                            } else {
                                hashtags[index] = "#"
                            }
                        }
                    )
                    if (index == hashtags.lastIndex) {
                        IconButton(onClick = {
                            hashtags.add("")
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Add Hashtag"
                            )
                        }
                    }
                }
            }
        }
    }
    return hashtags.toList()
}
// ハッシュタグリストのUIを実装E-----------------------------------------------------------------------

// 個々のハッシュタグを入力するテキストフィールドのUIを実装S-------------------------------------------------
@Composable
fun DynamicHashtagTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    onDelete: () -> Unit,
) {
    // 外部から渡された text が更新された場合に備えて内部状態も更新する
    var textFieldValue by remember { mutableStateOf(TextFieldValue(text, TextRange(text.length))) }

    // 親から渡された text が変更されたら内部状態を更新
    LaunchedEffect(text) {
        if (text != textFieldValue.text) {
            textFieldValue = TextFieldValue(text, TextRange(text.length))
        }
    }

    // フォーカス状態の管理
    var isFocused by remember { mutableStateOf(false) }

    val labelText = "タグ"
    val maxChars = 20  // '#' を除いた最大文字数

    // '#' を除いた文字数をカウントするヘルパー関数
    fun getTextLengthWithoutHash(currentText: String): Int {
        return if (currentText.startsWith("#")) currentText.length - 1 else currentText.length
    }

    // テキストのサイズ計測用インスタンス
    val textMeasurer = rememberTextMeasurer()
    val density = LocalDensity.current

    // フィールドが空の場合は labelText を基準とする
    val textToMeasure = if (textFieldValue.text.isEmpty()) labelText else textFieldValue.text

    // ラベルの幅を計測し、余白90.dpを追加
    val labelWidth = with(density) {
        textMeasurer.measure(
            text = labelText,
            style = TextStyle(fontSize = 16.sp)
        ).size.width.toDp()
    } + 90.dp

    // 入力テキストに基づいて動的な幅を算出
    val dynamicWidth = with(density) {
        val measuredTextWidth = textMeasurer.measure(
            text = textToMeasure,
            style = TextStyle(fontSize = 16.sp)
        ).size.width.toDp()
        max(measuredTextWidth + 70.dp, labelWidth)
    }
    // テキストフィールドの幅は最大 300.dp に制限
    val fieldWidth = dynamicWidth.coerceAtMost(300.dp)

    Column(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = { newValue ->
                // 改行文字を除去
                val filteredText = newValue.text.replace("\n", "")

                val cursorPosition = newValue.selection.start

                // #が削除されようとしている場合は#を維持
                if (!filteredText.startsWith("#") && textFieldValue.text.startsWith("#")) {
                    val restoredText = "#" + filteredText
                    textFieldValue = TextFieldValue(
                        text = restoredText,
                        selection = TextRange((cursorPosition + 1).coerceIn(1, restoredText.length))
                    )
                    return@OutlinedTextField
                }

                // 入力が空の場合はそのまま空文字をセット
                if (filteredText.isEmpty()) {
                    textFieldValue = newValue.copy(text = "", selection = TextRange(0))
                    onTextChanged("")
                } else {
                    val updatedText = if (!filteredText.startsWith("#")) "#$filteredText" else filteredText
                    val contentText = updatedText.removePrefix("#")
                    if (contentText.length <= maxChars) {
                        textFieldValue = newValue.copy(
                            text = updatedText,
                            selection = TextRange(updatedText.length)
                        )
                        onTextChanged(updatedText)
                    }
                }
            },
            label = { Text(labelText) },
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            modifier = Modifier
                .width(fieldWidth)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            // 後ろにつくアイコン
            // テキストをリセット
            trailingIcon = {
                IconButton(onClick = {
                    if (textFieldValue.text.isEmpty()) {
                        onDelete()
                    } else {
                        textFieldValue = TextFieldValue("", TextRange(0))
                        onTextChanged("")
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "テキストをクリア"
                    )
                }
            },
            // 文字数制限を超えたらエラー色
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (getTextLengthWithoutHash(textFieldValue.text) >= maxChars)
                    MaterialTheme.colorScheme.error
                else MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = if (getTextLengthWithoutHash(textFieldValue.text) >= maxChars)
                    MaterialTheme.colorScheme.error.copy(alpha = 0.5f)
                else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
            ),
            // ユーザーが入力したテキストに対して自動補正（オートコレクト）が無効になり、入力内容がそのまま反映される
            keyboardOptions = KeyboardOptions(autoCorrectEnabled = false)
        )
    }
}
// 個々のハッシュタグを入力するテキストフィールドのUIを実装E-------------------------------------------------

@Composable
fun SoundPost_Screen() {
    // Use LocalConfiguration to get the screen dimensions reliably
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    // スマホの横幅を取得
    val screenWidth = configuration.screenWidthDp.dp

    // どの円が選択されたか
    var selectedCircle by remember { mutableStateOf("") }

    // 保存用ハッシュタグリスト
    var tags: List<String> = remember { mutableStateListOf("") }

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),  // 親の幅いっぱいにする
                contentAlignment = Alignment.Center  // コンテンツを中央揃え
            ) {
                Row(
                    modifier = Modifier
                        .width(screenWidth * 0.9f)  // 90%の幅にする
                        .background(Color.LightGray),
                    horizontalArrangement = Arrangement.SpaceBetween,  // アイコンを左右の端に配置
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 左側の下書き保存アイコン
                    IconButton(onClick = { /* doSomething() for draft saving */ }) {
                        Icon(
                            imageVector = Icons.Default.DriveFileRenameOutline, // ペンマーク
                            contentDescription = "下書き保存"
                        )
                    }

                    // 右側の投稿アイコン
                    IconButton(onClick = { /* doSomething() for sending post */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "投稿"
                        )
                    }
                }
            }



        }
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

                //　イメージカラー行S----------------------------------------------------

                Box(
                    modifier = Modifier
                        .width(screenWidth * 0.9f)  // 開始位置を線の下に
                        .fillMaxWidth()
                ) {

                    // イメージカラーのテキストS------------------------------------
                    Text(
                        text = "イメージカラー",
                        modifier = Modifier
                            .align(Alignment.CenterStart)  // 左寄せ
                            .padding(top = 16.dp)  // 仕切りから少し間隔をあける
                    )
                    // イメージカラーのテキストE------------------------------------

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
                            fillColor = Color(0xFF4EB0F4),
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
                //　イメージカラー行E----------------------------------------------------

                // ハッシュタグS--------------------------------------------------------------
                Box(
                    modifier = Modifier
                        .width(screenWidth * 0.9f)  // 開始位置を線の下に
                        .fillMaxWidth()
                ) {

                    LazyColumn {
                        item {
                            tags = DynamicHashtagTextFields()
                            println("tags: $tags")
                        }
                    }
                }
                // ハッシュタグE--------------------------------------------------------------

                // 投稿データE---------------------------------------------------------------
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SOundpost() {
    val navController = NavController(LocalContext.current)
    VeilTheme {
        SoundPost_Screen(navController, soundView = SoundViewModel())
    }
}