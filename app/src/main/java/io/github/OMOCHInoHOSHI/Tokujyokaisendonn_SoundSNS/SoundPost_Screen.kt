package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import android.app.Activity
import android.graphics.Color.rgb
import android.graphics.Insets.add
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


// 通知画面
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SoundPost_Screen(navController: NavController){
    // Use LocalConfiguration to get the screen dimensions reliably
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    // スマホの横幅を取得
    val screenWidth = configuration.screenWidthDp.dp

    Scaffold(
        bottomBar = {BottomNavBar(navController)}
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

                    // カラー丸S-------------------------------------------------
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(top = 16.dp),
//                        verticalAlignment = Alignment.CenterVertically, // テキストと円を垂直方向の中央で揃える
                        horizontalArrangement = Arrangement.spacedBy(15.dp) // テキストと円の間隔を設定
                    ) {
                        // 円を描画
                        Circle_Draw(Color.Red)

                        Circle_Draw(Color.Blue)

                        Circle_Draw(Color.Green)

                        Circle_Draw(Color(0xFFFF47D3))

                    }
                    // カラー丸E-------------------------------------------------

                }
                //　イメージカラー列E----------------------------------------------------

                Box(
                    modifier = Modifier
                        .width(screenWidth * 0.9f)  // 開始位置を線の下に
                        .fillMaxWidth()
                ){
                    var text by remember { mutableStateOf("") }

//                    OutlinedTextField(
//                        value = text,
//                        onValueChange = { text = it },
//                        label = { Text("# ハッシュタグ") }
//                    )

//                    DynamicHashtagTextField()

//                    DynamicHashtagTextFields()

                    LazyColumn {
                        item {
                            FlowRow() {
                                DynamicHashtagTextFields()
                            }
                        }
                    }
//                    FlowRow()  {
//                        DynamicHashtagFields()
//                        // ハッシュタグリスト
//                        val textFieldStringval: MutableList<String> = mutableListOf(DynamicHashtagTextField())
//                        println("textFieldValue: " + textFieldStringval)
////
//
//                        if (textFieldStringval.isNotEmpty() && textFieldStringval.last().isNotBlank() && textFieldStringval.last() != "#") {
//                            println("最後の値は空ではありません: ${textFieldStringval.last()}")
////                            DynamicHashtagTextField()
//                            textFieldStringval.add(DynamicHashtagTextField())
//                            println("textFieldValue2: " + textFieldStringval)
//                        } else {
//                            println("最後の値が空です")
//                        }


//                    }
                }

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

                    // カラー丸S-------------------------------------------------
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
                //　イメージカラー列E----------------------------------------------------

                Box(
                    modifier = Modifier
                        .width(screenWidth * 0.9f)  // 開始位置を線の下に
                        .fillMaxWidth()
                ){
                    var text by remember { mutableStateOf("") }

//                    OutlinedTextField(
//                        value = text,
//                        onValueChange = { text = it },
//                        label = { Text("# ハッシュタグ") }
//                    )

                    DynamicHashtagTextField()
                }

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


@Composable
fun DynamicHashtagTextField():String {
    var text by remember { mutableStateOf("") } // 初期値を空に設定
    var isFocused by remember { mutableStateOf(false) }
    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(
            text = "",
            selection = TextRange(0)
        ))
    }
    val labelText = "タグ"
    val maxChars = 20 // 最大文字数を20文字に設定（#を除く）

    // #を除いた実際の文字数を計算する関数
    fun getTextLengthWithoutHash(text: String): Int {
        return if (text.startsWith("#")) {
            text.length - 1
        } else {
            text.length
        }
    }

    // Create a text measurer instance to measure text sizes
    val textMeasurer = rememberTextMeasurer()
    val density = LocalDensity.current

    // Measure the label's width using the desired text style and add extra space for padding.
    val labelWidth = with(density) {
        textMeasurer.measure(
            text = labelText,
            style = TextStyle(fontSize = 16.sp)
        ).size.width.toDp()
    } + 50.dp

    // Compute the dynamic width based on the input text.
    val dynamicWidth = remember(textFieldValue.text) {
        with(density) {
            val measuredTextWidth = textMeasurer.measure(
                text = if (textFieldValue.text.isEmpty()) labelText else textFieldValue.text,
                style = TextStyle(fontSize = 16.sp)
            ).size.width.toDp()
            max(measuredTextWidth + 50.dp, labelWidth)
        }
    }

    // Set an upper limit for the TextField's width
    val fieldWidth = dynamicWidth.coerceAtMost(300.dp)

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = { newValue ->
                // 改行文字を削除
                val filteredText = newValue.text.replace("\n", "")

                // #を除いた新しいテキストの長さをチェック
                val newTextLengthWithoutHash = getTextLengthWithoutHash(filteredText)

                if (newTextLengthWithoutHash <= maxChars) {
                    val cursorPosition = newValue.selection.start

                    // テキストが空になる場合は#を維持
                    if (filteredText.isEmpty() && textFieldValue.text.isNotEmpty()) {
                        textFieldValue = TextFieldValue(
                            text = "#",
                            selection = TextRange(1)
                        )
                        return@OutlinedTextField
                    }

                    // #が削除されようとしている場合は#を維持
                    if (!filteredText.startsWith("#") && textFieldValue.text.startsWith("#")) {
                        val restoredText = "#" + filteredText
                        textFieldValue = TextFieldValue(
                            text = restoredText,
                            selection = TextRange((cursorPosition + 1).coerceIn(1, restoredText.length))
                        )
                        return@OutlinedTextField
                    }

                    // 通常の入力処理
                    val updatedText = when {
                        filteredText.isEmpty() -> "#"
                        !filteredText.startsWith("#") -> "#$filteredText"
                        else -> filteredText
                    }

                    // カーソル位置の調整
                    val newCursorPosition = when {
                        !textFieldValue.text.startsWith("#") && updatedText.startsWith("#") ->
                            cursorPosition + 1
                        else -> cursorPosition
                    }.coerceIn(1, updatedText.length)

                    textFieldValue = TextFieldValue(
                        text = updatedText,
                        selection = TextRange(newCursorPosition)
                    )
                }
            },
            label = { Text(labelText) },
            modifier = Modifier
                .width(fieldWidth)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused && !isFocused && textFieldValue.text.isEmpty()) {
                        textFieldValue = TextFieldValue(
                            text = "#",
                            selection = TextRange(1)
                        )
                    }
                    isFocused = focusState.isFocused
                },
            textStyle = TextStyle(fontSize = 16.sp),
            singleLine = true,
            maxLines = 1,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (getTextLengthWithoutHash(textFieldValue.text) >= maxChars)
                    MaterialTheme.colorScheme.error
                else MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = if (getTextLengthWithoutHash(textFieldValue.text) >= maxChars)
                    MaterialTheme.colorScheme.error.copy(alpha = 0.5f)
                else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
            ),
            supportingText = {
                Text(
                    text = "${getTextLengthWithoutHash(textFieldValue.text)}/$maxChars",
                    color = if (getTextLengthWithoutHash(textFieldValue.text) >= maxChars)
                        MaterialTheme.colorScheme.error
                    else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        )
    }
    return textFieldValue.text
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DynamicHashtagTextFields() {
    // 初期状態として、ハッシュタグリストに1つのフィールド("#")を持たせる
    val hashtags = remember { mutableStateListOf("") }

    FlowRow(
        modifier = Modifier.padding(5.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        hashtags.forEachIndexed { index, text ->
            Row(verticalAlignment = Alignment.CenterVertically) {
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
                // 追加ボタンは、最後のテキストフィールドの横に配置する
                if (index == hashtags.lastIndex) {
                    IconButton(onClick = { hashtags.add("") }) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Hashtag")
                    }
                }
            }
        }
    }
}

@Composable
fun DynamicHashtagTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    onDelete: () -> Unit
) {
    // テキストフィールドの状態。初期状態は引数で指定された text（通常は空）になります。
    var textFieldValue by remember { mutableStateOf(TextFieldValue(text, TextRange(text.length))) }

    // フォーカス状態の管理（今回は自動挿入は行わないため、更新のみ）
    var isFocused by remember { mutableStateOf(false) }

    val labelText = "タグ"
    val maxChars = 21  // '#' を除いた最大文字数

    // '#' を除いた文字数をカウントするヘルパー関数
    fun getTextLengthWithoutHash(text: String): Int {
        return if (text.startsWith("#")) text.length - 1 else text.length
    }

    // テキストのサイズ計測用インスタンス
    val textMeasurer = rememberTextMeasurer()
    val density = LocalDensity.current

    // フィールドが空の場合は labelText を基準とする
    val textToMeasure = if (textFieldValue.text.isEmpty()) labelText else textFieldValue.text
    // ラベルの幅を計測し、余白60.dpを追加
    val labelWidth = with(density) {
        textMeasurer.measure(
            text = labelText,
            style = TextStyle(fontSize = 16.sp)
        ).size.width.toDp()
    } + 60.dp

    // 入力テキストに基づいて動的な幅を算出
    val dynamicWidth = with(density) {
        val measuredTextWidth = textMeasurer.measure(
            text = textToMeasure,
            style = TextStyle(fontSize = 16.sp)
        ).size.width.toDp()
        max(measuredTextWidth + 50.dp, labelWidth)
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
                // テキストが空になる場合は#を維持
//                if (filteredText.isEmpty() && textFieldValue.text.isNotEmpty()) {
//                    textFieldValue = TextFieldValue(
//                        text = "#",
//                        selection = TextRange(1)
//                    )
//                    return@OutlinedTextField
//                }

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
                    // 入力値の先頭に "#" が付いていなければ自動付与
                    val updatedText = if (!filteredText.startsWith("#")) "#$filteredText" else filteredText
                    val contentText = updatedText.removePrefix("#")
                    if (contentText.length <= maxChars) {
                        // カーソル位置はテキストの終端に設定
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
                    // 初回フォーカス時に自動挿入を行う処理は削除し、初期状態は空のままとする
                },
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
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (getTextLengthWithoutHash(textFieldValue.text) >= maxChars)
                    MaterialTheme.colorScheme.error
                else MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = if (getTextLengthWithoutHash(textFieldValue.text) >= maxChars)
                    MaterialTheme.colorScheme.error.copy(alpha = 0.5f)
                else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
            ),
            keyboardOptions = KeyboardOptions(autoCorrect = false)
        )
    }
}