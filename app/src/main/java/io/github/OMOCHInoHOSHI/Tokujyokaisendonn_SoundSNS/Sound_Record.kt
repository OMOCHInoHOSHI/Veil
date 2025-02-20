package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import java.io.IOException
import android.Manifest

// 指定された秒数録音する関数
fun Sound_Record(){


}


// Androidアプリケーションにおいて音声録音と再生を行うためのクラスS-------------------------------------------------
private const val LOG_TAG = "AudioRecordTest"
private const val REQUEST_RECORD_AUDIO_PERMISSION = 200

class AudioRecordTest : AppCompatActivity() {

    // 録音ファイルの名前（パス）を保持する変数。
    private var fileName: String = ""

    // 録音開始・停止ボタン
    private var recordButton: RecordButton? = null
    // 録音を行うためのMediaRecorderオブジェクト
    private var recorder: MediaRecorder? = null

    // 再生開始・停止ボタン
    private var playButton: PlayButton? = null
    // 再生を行うためのMediaPlayerオブジェクト
    private var player: MediaPlayer? = null

    // 録音パーミッションが許可されているかどうかを保持するフラグ。
    private var permissionToRecordAccepted = false
    // リクエストするパーミッションの配列
    private var permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)

    // パーミッションリクエストの結果を処理します。録音パーミッションが許可されていない場合、アクティビティを終了します。
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionToRecordAccepted = if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        } else {
            false
        }
        if (!permissionToRecordAccepted) finish()
    }

    // 録音の開始・停止を制御します。startがtrueの場合は録音を開始し、そうでない場合は録音を停止
    private fun onRecord(start: Boolean) = if (start) {
        startRecording()
    } else {
        stopRecording()
    }

    // 再生の開始・停止を制御
    private fun onPlay(start: Boolean) = if (start) {
        startPlaying()
    } else {
        stopPlaying()
    }

    // MediaPlayerを使用して録音ファイルの再生
    @OptIn(UnstableApi::class)
    private fun startPlaying() {
        player = MediaPlayer().apply {
            try {
                setDataSource(fileName)
                prepare()
                start()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }
        }
    }

    // 再生を停止し、MediaPlayerオブジェクトを解放します
    private fun stopPlaying() {
        player?.release()
        player = null
    }

    // MediaRecorderを使用して音声の録音を開始
    @OptIn(UnstableApi::class)
    private fun startRecording() {
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(fileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }

            start()
        }
    }

    // 録音を停止し、MediaRecorderオブジェクトを解放
    private fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
    }

    // 録音を開始・停止するボタンです。ボタンのテキストとクリックリスナーを設定
    internal inner class RecordButton(ctx: Context) : androidx.appcompat.widget.AppCompatButton(ctx) {

        var mStartRecording = true

        var clicker: OnClickListener = OnClickListener {
            onRecord(mStartRecording)
            text = when (mStartRecording) {
                true -> "Stop recording"
                false -> "Start recording"
            }
            mStartRecording = !mStartRecording
        }

        init {
            text = "Start recording"
            setOnClickListener(clicker)
        }
    }

    // 再生を開始・停止するボタンです。ボタンのテキストとクリックリスナーを設定
    internal inner class PlayButton(ctx: Context) : androidx.appcompat.widget.AppCompatButton(ctx) {
        var mStartPlaying = true
        var clicker: OnClickListener = OnClickListener {
            onPlay(mStartPlaying)
            text = when (mStartPlaying) {
                true -> "Stop playing"
                false -> "Start playing"
            }
            mStartPlaying = !mStartPlaying
        }

        init {
            text = "Start playing"
            setOnClickListener(clicker)
        }
    }

    // アクティビティが作成されたときに呼び出されます。ファイル名を設定し、パーミッションをリクエストし、ボタンをレイアウトに追加
    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)

        // Record to the external cache directory for visibility
        fileName = "${externalCacheDir?.absolutePath}/audiorecordtest.3gp"

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION)

        recordButton = RecordButton(this)
        playButton = PlayButton(this)
        val ll = LinearLayout(this).apply {
            addView(recordButton,
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    0f))
            addView(playButton,
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    0f))
        }
        setContentView(ll)
    }

    // 録音・再生オブジェクトを解放
    override fun onStop() {
        super.onStop()
        recorder?.release()
        recorder = null
        player?.release()
        player = null
    }
}