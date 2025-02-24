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
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.openapitools.client.apis.UserApi
import org.openapitools.client.models.RequestUserSignupRequest
import java.io.File
import java.io.IOException
import org.openapitools.client.apis.FileApi

// 音の再生状態などを管理するViewModelS-------------------------------------------------
class SoundViewModel : ViewModel() {

    // 録音中かの確認
    private val _recording = MutableStateFlow(false)
    val recording: StateFlow<Boolean> get() = _recording

    // 再生中の確認
    private val _soundPlaying = MutableStateFlow(false)
    val soundPlaying: StateFlow<Boolean> get() = _soundPlaying

    // コールバック関数
    // 変更を外部に通知
    private var onSoundPlayingStatusChanged: ((Boolean) -> Unit)? = null

    private var onRecordingStatusChanged: ((Boolean) -> Unit)? = null

    // 再生中かを変更する関数
    fun setSoundPlaying(success: Boolean) {
        _soundPlaying.value = success
        // 状態を変更する際にコールバックを実行
        onSoundPlayingStatusChanged?.invoke(success)
    }

    // 録音中かを変更する関数
    fun setRecording(success: Boolean) {
        _recording.value = success
        // 状態を変更する際にコールバックを実行
        onRecordingStatusChanged?.invoke(success)
    }

    // 現在の soundPlaying の状態を取得
    fun checkSoundPlaying(): Boolean {
        return _soundPlaying.value
    }

    // 現在の recording の状態を取得
    fun checkRecording(): Boolean {
        return _recording.value
    }
}
// 音の再生状態などを管理するViewModelE-------------------------------------------------



private const val LOG_TAG = "AudioRecordTest"
const val REQUEST_RECORD_AUDIO_PERMISSION = 200

class AudioRecordTest(private val context: Context, val soundView: SoundViewModel/* = SoundViewModel()*/) {

    private var fileName: String = ""
    private var recorder: MediaRecorder? = null
    private var player: MediaPlayer? = null

    var uploadState = false

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
            Thread.sleep(500)
        } else {
//            Thread.sleep(200)  // 0.1秒待ってから stop()（調整可能）
            if(soundView.checkRecording()){
                stopRecording()
            }
        }
    }

    // 再生開始・停止の制御
    fun onPlay(start: Boolean) {
        if (start) {
            soundView.setSoundPlaying(true)
            startPlaying()
        } else {
            stopPlaying()
            soundView.setSoundPlaying(false)
        }
    }

    private fun startPlaying() {
        player = MediaPlayer().apply {
            try {
                setDataSource(fileName)
                prepare()
                start()
                Toast.makeText(context, "再生中", Toast.LENGTH_SHORT).show()

                // 再生終了を検知するリスナーを設定
                setOnCompletionListener {
                    soundView.setSoundPlaying(false)
                    Toast.makeText(context, "再生終了", Toast.LENGTH_SHORT).show()
                    stopPlaying()  // MediaPlayer を解放
                }
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
            soundView.setRecording(true)
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
            soundView.setRecording(false)

        }
    }

    fun callUploadSound(fileApi: FileApi, col: String, tags: String) {
        CoroutineScope(Dispatchers.IO).launch { // コルーチンスコープ内で呼び出す
            uploadSound(fileApi, col, tags)
        }
    }
    suspend fun uploadSound(fileApi: FileApi, col:String, tags:String){

        val userApi = ApiManager.userApi

        val uid = getAPIViewModel.get_UserId()

        println("uid = $uid")



        try {
            val response = withContext(Dispatchers.IO){
                fileApi.usersUserIdAudiosPost(uid, File(fileName), "#000000", "tagA,tagB" )
            }
            Log.i("UPLOAD", "uid = $uid file = $fileName")
            Log.d("UPLOAD", "アップロード成功: $response")


        } catch (e: Exception) {
            Log.e("UPLOAD", "アップロードエラー: ${e.javaClass.name}")
        }



    }
}