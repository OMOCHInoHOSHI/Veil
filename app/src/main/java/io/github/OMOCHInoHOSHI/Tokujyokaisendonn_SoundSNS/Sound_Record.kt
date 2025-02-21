package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import coil.compose.AsyncImagePainter
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.File
import java.io.IOException

// 音の再生状態などを管理するViewModelS-------------------------------------------------
class soundViewModel : ViewModel() {

    // 再生中の確認h
    val _soudplaying = MutableStateFlow(false)

    // コールバック関数
    // 変更を外部に
    private var onSoudPlayingStatusChanged: ((Boolean) -> Unit)? = null

    // 再生中かを変更する関数
    fun SetSoudPlaying(success: Boolean) {
        _soudplaying.value = success

        // 状態を変更する際にコールバックを実行
        onSoudPlayingStatusChanged?.invoke(success)
    }

    // 現在の uploadSuccess の状態を取得
    fun CheckSoudPlaying(): Boolean {
        return _soudplaying.value
    }
}
// 音の再生状態などを管理するViewModelE-------------------------------------------------



private const val LOG_TAG = "AudioRecordTest"
const val REQUEST_RECORD_AUDIO_PERMISSION = 200

class AudioRecordTest(private val context: Context) {

    private var fileName: String = ""
    private var recorder: MediaRecorder? = null
    private var player: MediaPlayer? = null

    init {
        // 外部キャッシュディレクトリを利用して出力ファイルのパスを作成
        // ※context を利用しているので、Activity 以外のクラスからも利用できます。
        val cacheDir = context.externalCacheDir
        if (cacheDir != null) {
            fileName = "${cacheDir.absolutePath}/audiorecordtest.3gp"
        } else {
            Log.e(LOG_TAG, "外部キャッシュディレクトリが取得できません")
        }
    }

    // 録音開始・停止の制御
    fun onRecord(start: Boolean) {
        if (start) {
            startRecording()
        } else {
            Thread.sleep(100)  // 0.1秒待ってから stop()（調整可能）
            stopRecording()
        }
    }

    // 再生開始・停止の制御
    fun onPlay(start: Boolean) {
        if (start) {
            startPlaying()
        } else {
            stopPlaying()
        }
    }

    private fun startPlaying() {
        player = MediaPlayer().apply {
            try {
                setDataSource(fileName)
                prepare()
                start()
                Toast.makeText(context, "再生中", Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "MediaPlayer prepare() failed: ${e.message}")
            }
        }
    }

    private fun stopPlaying() {
        player?.release()
        player = null
    }

    private fun startRecording() {
        if (recorder != null) {
            Log.e(LOG_TAG, "Recorder はすでに初期化されています")
            return
        }

        val outputFile = File(fileName)
        outputFile.parentFile?.mkdirs()

        try {
            recorder = MediaRecorder().apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                setOutputFile(fileName)
                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                prepare()
                start()
            }
            Toast.makeText(context, "録音中", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(context, "録音開始エラー: ${e.message}", Toast.LENGTH_SHORT).show()
            Log.e(LOG_TAG, "録音開始エラー: ${e.message}")
            recorder?.release()
            recorder = null
        }
    }


    private fun stopRecording() {
        try {
            if (recorder != null) {
                recorder?.stop()  // MediaRecorder の状態を確認してから stop()
                Toast.makeText(context, "録音停止", Toast.LENGTH_SHORT).show()
            } else {
                Log.e(LOG_TAG, "Recorder が null のため stop() を呼べません")
            }
        } catch (e: IllegalStateException) {
            Log.e(LOG_TAG, "MediaRecorder stop() invalid state: ${e.message}")
        } finally {
            recorder?.release()
            recorder = null
        }
    }
}