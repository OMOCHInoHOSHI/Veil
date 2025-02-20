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
import java.io.File
import java.io.IOException

private const val LOG_TAG = "AudioRecordTest"
private const val REQUEST_RECORD_AUDIO_PERMISSION = 200

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
        // 出力先のディレクトリが存在しているか確認し、存在しなければ作成
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
        } catch (e: IllegalStateException) {
            Log.e(LOG_TAG, "MediaRecorder start() invalid state: ${e.message}")
        } catch (e: IOException) {
            Log.e(LOG_TAG, "MediaRecorder prepare() failed: ${e.message}")
        }
    }

    private fun stopRecording() {
        try {
            recorder?.stop()
        } catch (e: IllegalStateException) {
            Log.e(LOG_TAG, "MediaRecorder stop() invalid state: ${e.message}")
        }
        recorder?.release()
        recorder = null
    }
}