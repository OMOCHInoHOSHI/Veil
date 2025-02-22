package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun Notice_Screen(navController: NavController) {

    val soundView: SoundViewModel = viewModel()
    val isPlaying by soundView.soundPlaying.collectAsState()

    var play by remember { mutableStateOf(isPlaying) }

    println("isPlaying = $play")

    // Compose 内では LocalContext.current を使って Context を取得
    val context = LocalContext.current
    // AudioRecordTest のインスタンスを生成（Activity のライフサイクル外でも利用可能なようにコンストラクタで Context を渡しています）
    val audioRecordTest = AudioRecordTest(context)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(100.dp),
        contentAlignment = Alignment.Center

    ) {


        Column {
            // 録音開始ボタン
            IconButton(
                onClick = { audioRecordTest.onRecord(true) },
                modifier = Modifier.padding(0.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Mic,
                    contentDescription = "録音開始"
                )
            }
            // 再生開始ボタン
            IconButton(
                onClick = { audioRecordTest.onPlay(true) },
                modifier = Modifier.padding(0.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "再生開始"
                )
            }
            // 停止ボタン（録音・再生の両方を停止）
            IconButton(
                onClick = {
                    audioRecordTest.onRecord(false)
                    audioRecordTest.onPlay(false)
                },
                modifier = Modifier.padding(0.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Stop,
                    contentDescription = "停止"
                )
            }
        }
    }
}