package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import androidx.core.app.ActivityCompat
import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import android.app.AlertDialog

// シングルトンなクラス（１つしか生成されないことが保証されたクラス）を生成
object AudioPermissionHelper {

    // オーディオ録音パーミッションに対するリクエストコード
    const val REQUEST_RECORD_AUDIO_PERMISSION = 200

    // RECORD_AUDIO パーミッションが許可されているか確認します。
    // 許可されていない場合、説明ダイアログを表示するか直接パーミッションを要求します。
    fun checkAudioPermission(activity: AppCompatActivity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            // パーミッションが以前に拒否された場合、説明が必要かどうかをチェックします
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECORD_AUDIO)) {
                showPermissionRationaleDialog(activity)
            } else {
                requestAudioPermission(activity)
            }
        } else {
            // すでにパーミッションが許可されている場合は、オーディオ録音の処理を実行します
        }
    }

    // オーディオ録音パーミッションが必要な理由を説明するダイアログを表示します
    private fun showPermissionRationaleDialog(activity: AppCompatActivity) {
        AlertDialog.Builder(activity)
            .setTitle("録音パーミッションが必要です")
            .setMessage("このアプリはオーディオ録音のためにマイクへのアクセスが必要です。")
            .setPositiveButton("許可") { _, _ ->
                requestAudioPermission(activity)
            }
            .setNegativeButton("拒否") { dialog, _ ->
                dialog.dismiss()
                // パーミッション拒否時の処理を必要に応じて実装してください
            }
            .show()
    }

    // RECORD_AUDIO パーミッションを要求します
    fun requestAudioPermission(activity: AppCompatActivity) {
        val permissions = arrayOf(Manifest.permission.RECORD_AUDIO)
        ActivityCompat.requestPermissions(activity, permissions, REQUEST_RECORD_AUDIO_PERMISSION)
    }
}
