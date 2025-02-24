package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat


// 録音パーミッション申請S------------------------------------------------------
@Composable
fun PermissionRequestScreen():Boolean {
    // パーミッションの状態を保持するための変数 hasPermission を定義
    var hasPermission by remember { mutableStateOf(false) }

    // LocalContext を使用して現在のアクティビティを取得
    val activity = LocalContext.current

    // ActivityResultLauncher を使用してパーミッションのリクエストを行う
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        // パーミッションが許可されたかどうかを hasPermission に設定
        hasPermission = isGranted
    }

    // LaunchedEffect を使用して初期化時にパーミッションの状態を確認およびリクエスト
    LaunchedEffect(Unit) {
        // パーミッションが既に許可されているか確認
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO) -> {
                // パーミッションが許可されている場合、hasPermission を true に設定
                hasPermission = true
            }
            else -> {
                // パーミッションが許可されていない場合、リクエストを行う
                requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
            }
        }
    }

    return hasPermission
}
// 録音パーミッション申請E------------------------------------------------------