package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Drafts
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS.ui.theme.VeilTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.openapitools.client.apis.UserApi
import org.openapitools.client.models.RequestUserSignupRequest

// 画面遷移先S-------------------------
enum class Nav {
//    LoginScreen,//ログイン画面
    Home_Screen, //ホーム画面
    Notice_Screen, //通知画面
    SoundPost_Screen, //音投稿準備画面
    MessageChat_Screen, //メッセージチャット画面
    MessageSelect_Screen, // チャット選択画面
    ChatHistory_Screen, // チャット履歴画面
    Profile_Screen,     // マイページ画面
    ProfileCreate_Screen, // マイページ作成画面
    Playlist_Screen, // プレイリスト画面
    PlaylistCreate_Screen, // プレイリスト作成画面
}
// 画面遷移先E-------------------------

//画面遷移の設定　どこのページへ移動するかnavControllerに定義する S--------------------------------------------------
@Composable
fun DisplayNav(){


//    val userApi = ApiManager.userApi
//
//    LaunchedEffect(Unit) { // LaunchedEffectを使用
//
//        try {
//            val response = withContext(Dispatchers.IO) { // ネットワーク処理をIOスレッドで実行
//                userApi.usersMeGet()
//            }
//            val (createdAt, email, id, name, updatedAt) = response
//            Log.i("FetchUserInfo", "Email: $email, ID: $id, Name: $name")
//            Log.i("FetchUserInfo", "User info retrieved: $response")
//        } catch (e: Exception) {
//            Log.e("FetchUserInfo", "Failed to retrieve user info", e)
//        }
//
//    }

    // NavControllerを定義
    val navController = rememberNavController()

    val soundView = SoundViewModel()

    // NavHostを作成
    // startDestinationは最初に表示するページS----------------------------------------------
    NavHost(navController = navController,
        startDestination = Nav.Home_Screen.name  //ホーム画面を最初に表示
    ) {
        // ルート名：Home_Screen　Home_Screenに遷移
        composable(route = Nav.Home_Screen.name) {
            Home_Screen(navController = navController)
        }

        // ルート名：SoundPost_Screen　SoundPost_Screenに遷移
        composable(route = Nav.SoundPost_Screen.name) {
            SoundPost_Screen(navController = navController, soundView)
        }

        // ルート名：Notice_Screen　Notice_Screenに遷移
        composable(route = Nav.Notice_Screen.name) {
            Notice_Screen(navController = navController)
        }

        // ルート名：MessageChat_Screen　MessageChat_Screenに遷移
        composable(route = "MessageChat_Screen/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            MessageChat_Screen(navController = navController, username = username)
        }

        // ルート名：MessageSelect_Screen　MessageSelect_Screenに遷移
        composable(route = Nav.MessageSelect_Screen.name) {
            MessageSelect_Screen(navController = navController)
        }

        // ルート名：ChatHistory_Screen　ChatHistory_Screenに遷移
        composable(route = Nav.ChatHistory_Screen.name) {
            ChatHistory_Screen(navController = navController)
        }

        // ルート名：Profile_Screen　Profile_Screenに遷移
        composable(route = Nav.Profile_Screen.name) {
            Profile_Screen(navController = navController)
        }

        // ルート名：ProfileCreate_Screen　ProfileCreate_Screenに遷移
        composable(route = Nav.ProfileCreate_Screen.name) {
            ProfileCreate_Screen(navController = navController)
        }

        // ルート名：Playlist_Screen　Playlist_Screenに遷移
        composable(route = Nav.Playlist_Screen.name) {
            Playlist_Screen(navController = navController)
        }

        // ルート名：PlaylistCreate_Screen　PlaylistCreate_Screenに遷移
        composable(route = Nav.PlaylistCreate_Screen.name) {
            PlaylistCreate_Screen(navController = navController)
        }
    }
    // startDestinationは最初に表示するページE----------------------------------------------

}
//画面遷移の設定　どこのページへ移動するかnavControllerに定義する E--------------------------------------------------

// 下のナビゲーションバーセットS----------------------------------------------------------------------------------------
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BottomNavBar(
    navController: NavController,
    soundView: SoundViewModel = viewModel() // デフォルト引数
) {

    //この関数は位置の指定をしていません。呼び出し側で管理してください

    // 録音パーミッションの許可
    val recordPermission = PermissionRequestScreen()
    val context = LocalContext.current

    // AudioRecordTest のインスタンスを生成（Activity のライフサイクル外でも利用可能なようにコンストラクタで Context を渡しています）
    val audioRecordTest = AudioRecordTest(context, soundView)

    // 選択管理
    var selectedTab by remember { mutableStateOf(0) }

    // navControllerの現在の画面ルートを取得
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // ナビゲーションバー
    NavigationBar {
        // Home遷移アイコン
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, "ホーム") },
//            label = { Text("ホーム") },
            // 現在のルートがHome_Screenなら選択状態にする
            selected = currentRoute == Nav.Home_Screen.name,

            onClick = {
                if (currentRoute != Nav.Home_Screen.name) {
                    navController.navigate(Nav.Home_Screen.name)
                }
            }
        )

        // マイクアイコン
//        NavigationBarItem(
//            icon = { Icon(Icons.Default.Mic, "録音") },
////            label = { Text("録音") },
//            // 現在のルートがSoundPost_Screenなら選択状態にする
//            selected = currentRoute == Nav.SoundPost_Screen.name,
//            onClick = {
//                // 録音許可が無いと遷移しない
//                if(recordPermission){
//                    if (currentRoute != Nav.SoundPost_Screen.name) {
//                        navController.navigate(Nav.SoundPost_Screen.name)
//                    }
//                }
//                else {
//                    Toast.makeText(context, "録音許可が必要です", Toast.LENGTH_SHORT).show()
//                }
//            }
//        )
        NavigationBarItem(
            icon = {
                Box(
                    modifier = Modifier.pointerInteropFilter { event ->

                        if (!recordPermission) {
                            // 録音許可がない場合、何もせず処理を中断
                            Toast.makeText(context, "録音許可が必要です", Toast.LENGTH_SHORT).show()
                            return@pointerInteropFilter false
                        }

                        when (event.action) {
                            // 押した時の動作
                            MotionEvent.ACTION_DOWN -> {
                                // 録音開始
                                audioRecordTest.onRecord(true)
                                true
                            }
                            // 話した時の動作
                            MotionEvent.ACTION_UP -> {
                                // 録音終了
                                audioRecordTest.onRecord(false)
                                if(currentRoute != Nav.SoundPost_Screen.name){
                                    // 録音終了
//                                    audioRecordTest.onRecord(false)
                                    navController.navigate(Nav.SoundPost_Screen.name)

                                }
                                true
                            }

                            // タッチがキャンセルされた場合の処理
                            MotionEvent.ACTION_CANCEL -> {
                                audioRecordTest.onRecord(false)
                                true
                            }

                            else -> false
                        }
                    }
                ) {
                    Icon(Icons.Default.Mic, "録音")
                }
            },
            selected = currentRoute == Nav.SoundPost_Screen.name,
            onClick = {
//                if (recordPermission) {
//                    if (currentRoute != Nav.SoundPost_Screen.name) {
//                        navController.navigate(Nav.SoundPost_Screen.name)
//                    }
//                } else {
//                    Toast.makeText(context, "録音許可が必要です", Toast.LENGTH_SHORT).show()
//                }
            }
        )

        // 通知アイコン
        NavigationBarItem(
            icon = { Icon(Icons.Default.Notifications, "通知") },
//            label = { Text("通知") },
            // 現在のルートがNotice_Screenなら選択状態にする
            selected = currentRoute == Nav.Notice_Screen.name,
            onClick = {
                if (currentRoute != Nav.Notice_Screen.name) {
                    navController.navigate(Nav.Notice_Screen.name)
                }
            }
        )
    }
}
// 下のナビゲーションバーセットE----------------------------------------------------------------------------------------

//　トップバーセットS-------------------------------------------------------------------------------------------------
// タイトルとアイコンS-------------------------------------------------------------------
//@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar_Screen(navController: NavController){
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text("Veil ", fontWeight = FontWeight.Bold) },

        actions = {
            // プロフィールアイコン
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(Icons.Default.Person, contentDescription = "プロフィール")
            }

            // ドロップダウンメニューを出す　//直接フラグ操作
            DropDownMenu_View(showMenu, onDismiss = { showMenu = false }, navController)
        }

    )

}
// タイトルとアイコンE-------------------------------------------------------------------

// アイコン押した時のドロップダウンメニューS-------------------------------------------------
@Composable
fun DropDownMenu_View(showMenu: Boolean, onDismiss: () -> Unit, navController: NavController) {
    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = onDismiss
    ) {
        // マイページ
        DropdownMenuItem(
            text = { Text("マイページ") },
            onClick = {
                onDismiss()
                navController.navigate("Profile_Screen") },
            leadingIcon = {
                Icon(Icons.Default.AccountCircle, contentDescription = "マイページ")
            }
        )

        // メッセージ
        DropdownMenuItem(
            text = { Text("メッセージ") },
            onClick = {
                onDismiss()
                navController.navigate(Nav.ChatHistory_Screen.name) },
            leadingIcon = {
                Icon(Icons.AutoMirrored.Filled.Message, contentDescription = "メッセージ")
            }
        )

        // 下書き一覧
        DropdownMenuItem(
            text = { Text("下書き一覧") },
            onClick = { onDismiss() /*TODO*/ },
            leadingIcon = {
                Icon(Icons.Default.Edit, contentDescription = "下書き一覧")
            }
        )

        // ユーザ検索
        DropdownMenuItem(
            text = { Text("ユーザ検索") },
            onClick = { onDismiss() /*TODO*/ },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "ユーザ検索")
            }
        )

        // 設定
        DropdownMenuItem(
            text = { Text("設定") },
            onClick = { onDismiss() /*TODO*/ },
            leadingIcon = {
                Icon(Icons.Default.Settings, contentDescription = "設定")
            }
        )

        // ログアウト
        DropdownMenuItem(
            text = { Text("ログアウト") },
            onClick = { onDismiss() /*TODO*/ },
            leadingIcon = {
                Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "ログアウト")
            }
        )
    }
}
// アイコン押した時のドロップダウンメニューE-------------------------------------------------

//　トップバーセットE-------------------------------------------------------------------------------------------------