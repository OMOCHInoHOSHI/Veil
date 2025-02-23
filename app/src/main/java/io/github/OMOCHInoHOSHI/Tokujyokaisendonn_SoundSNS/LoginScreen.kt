package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.openapitools.client.apis.UserApi
import org.openapitools.client.models.RequestUserSignupRequest
import java.io.IOException
import java.util.concurrent.TimeUnit

@Preview
@Composable
fun LoginScreen(): Boolean {

    val context = LocalContext.current

    // タイムアウトを30秒に設定した OkHttpClient を生成
//    val client = createOkHttpClientWithTimeout(30)

    // ログイン結果
    var loginState by remember { mutableStateOf(false) }

    // ユーザー名
    var user:String? by remember { mutableStateOf(null) }

    // メールアドレス
    var email: String? by remember { mutableStateOf(null) }
    // パスワード
    var password: String? by remember { mutableStateOf(null) }


    // おれのローカル
    val userApi = UserApi("http://192.168.1.9:8088/api")
//    val userApi = UserApi("http://127.0.0.1:8088")
//    val users = userApi

    Scaffold(
        content =  { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding) // ここで innerPadding を適用
            )
            {
//                Image(
//                    painter = painterResource(id = R.drawable.risrisris),
//                    contentDescription = "背景画像",
//                    contentScale = ContentScale.FillHeight,
//                    modifier = Modifier.fillMaxSize()
//                )

                // メールアドレスとパスワードの入力UIS----------------------------------------------------------------------
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                ) {
                    // メアドS------------------------------------------------------------
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "ユーザー名(3~32文字)",
                        style = TextStyle(color = ColerSelect(), fontSize = 14.sp),
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = user ?: "",
                        onValueChange = { user = it },
                        label = { Text("ユーザー名") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    // メアドE------------------------------------------------------------

                    // メアドS------------------------------------------------------------
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "メールアドレスを入力",
                        style = TextStyle(color = ColerSelect(), fontSize = 14.sp),
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = email ?: "",
                        onValueChange = { email = it },
                        label = { Text("メールアドレス") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    // メアドE------------------------------------------------------------

                    Spacer(modifier = Modifier.height(16.dp))

                    // パスワード入力S------------------------------------------------------
                    Text(
                        text = "パスワードは6文字以上",
                        style = TextStyle(color = ColerSelect(), fontSize = 14.sp),
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = password ?: "",
                        onValueChange = { password = it },
                        label = { Text("パスワード") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    // パスワード入力E------------------------------------------------------

                    Spacer(modifier = Modifier.height(16.dp))

                    // ログインS-----------------------------------------------------------
                    Button(
//                        onClick = { /* ログイン処理 */ },
                        onClick = { loginState = true

                            if(loginState){
                                Toast.makeText(context, "ログイン成功", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                Toast.makeText(context, "ログイン失敗", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("ログイン", fontSize = 18.sp)
                    }
                    // ログインE-----------------------------------------------------------

                    Spacer(modifier = Modifier.height(16.dp))

                    // 新規登録S-----------------------------------------------------------
//                    Button(
//                        onClick = { /* 新規登録処理 */
//                            val res = userApi.signupPost(RequestUserSignupRequest(user!!, email!!, password!!))
//                            println("res = $res")
//                                  },
//                        modifier = Modifier.fillMaxWidth()
//                    ) {
//                        Text("新規登録", fontSize = 18.sp)
//                    }
                    Button(
                        onClick = {
                            // 入力値が空でないことを簡易チェック
//                            if (user?.isNotBlank() ?:  && email.isNotBlank() && password.isNotBlank()) {
                            // バックグラウンドスレッドで API コールを実行
                            CoroutineScope(Dispatchers.IO).launch {
                                loginState = performSignup(userApi, user!!, email!!, password!!)
                            }
//                            } else {
//                                Log.e("Signup", "入力内容を確認してください。")
//                            }

                            // 登録の視覚的コールバック
                            if(loginState){
                                Toast.makeText(context, "登録成功", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                Toast.makeText(context, "登録失敗", Toast.LENGTH_SHORT).show()
                            }


                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("新規登録", fontSize = 18.sp)
                    }
                    // 新規登録E-----------------------------------------------------------

                }
            }
        }
        // メールアドレスとパスワードの入力UIS----------------------------------------------------------------------

    )

    return loginState
}



//// 指定秒数のタイムアウトを設定した OkHttpClient を生成する関数
//fun createOkHttpClientWithTimeout(timeoutSeconds: Long): OkHttpClient {
//    return OkHttpClient.Builder()
//        .connectTimeout(timeoutSeconds, TimeUnit.SECONDS)
//        .readTimeout(timeoutSeconds, TimeUnit.SECONDS)
//        .writeTimeout(timeoutSeconds, TimeUnit.SECONDS)
//        .build()
//}

//// ネットワーク接続を行い、呼び出し結果・エラーを Logcat に出力する関数
//suspend fun performNetworkCall(client: OkHttpClient) {
//    // URL に正しいポート番号（例：8088）を指定する
//    val request = okhttp3.Request.Builder()
//        .url("http://192.168.1.9:8088")
//        .build()
//
//    try {
//        val response = client.newCall(request).execute()
//        if (response.isSuccessful) {
//            Log.i("NetworkCall", "Response: ${response.body?.string()}")
//        } else {
//            Log.e("NetworkCall", "サーバーエラー – コード: ${response.code}")
//        }
//    } catch (e: IOException) {
//        e.printStackTrace()
//        Log.e("NetworkCall", "ネットワーク呼び出しに失敗しました", e)
//    }
//}

// 新規登録のための API 呼び出し関数
suspend fun performSignup(userApi: UserApi, user: String, email: String, password: String):Boolean {

    Log.i("Signup", "user: $user, email: $email, password: $password")

    try {
        Log.i("Signup", "Signup response: ${userApi.baseUrl}")
        // API 呼び出しのためのリクエストオブジェクトを作成
        val signupRequest = RequestUserSignupRequest(email,user,  password)
        // signupPost を呼び出して結果を取得
        val res = userApi.signupPost(signupRequest)
        Log.i("Signup", "Signup response: $res")

        return true
    } catch (e: Exception) {
        e.printStackTrace()
        Log.e("Signup", "登録API呼び出しに失敗しました", e)

        return false
    }

}