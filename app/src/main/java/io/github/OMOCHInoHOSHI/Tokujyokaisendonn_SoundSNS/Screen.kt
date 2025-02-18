package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.Message
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS.ui.theme.VeilTheme

// 画面遷移先S-------------------------
enum class Nav {
//    LoginScreen,//ログイン画面
    Home_Screen, //ホーム画面
    Notice_Screen, //通知画面
    SoundPost_Screen, //音投稿準備画面
}
// 画面遷移先E-------------------------

//画面遷移の設定　どこのページへ移動するかnavControllerに定義する S--------------------------------------------------
@Composable
fun DisplayNav(){
    // NavControllerを定義
    val navController = rememberNavController()

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
            SoundPost_Screen(navController = navController)
        }

        // ルート名：Notice_Screen　Notice_Screenに遷移
        composable(route = Nav.Notice_Screen.name) {
            Notice_Screen(navController = navController)
        }
    }
    // startDestinationは最初に表示するページE----------------------------------------------

}
//画面遷移の設定　どこのページへ移動するかnavControllerに定義する E--------------------------------------------------

// 下のナビゲーションバーセットS----------------------------------------------------------------------------------------
@Composable
fun BottomNavBar(navController: NavController) {

    //この関数は位置の指定をしていません。呼び出し側で管理してください

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
        NavigationBarItem(
            icon = { Icon(Icons.Default.Mic, "録音") },
//            label = { Text("録音") },
            // 現在のルートがSoundPost_Screenなら選択状態にする
            selected = currentRoute == Nav.SoundPost_Screen.name,
            onClick = {
                if (currentRoute != Nav.SoundPost_Screen.name) {
                    navController.navigate(Nav.SoundPost_Screen.name)
                }
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
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar_Screen(){
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text("Veil ", fontWeight = FontWeight.Bold) },

        actions = {
            // プロフィールアイコン
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(Icons.Default.Person, contentDescription = "プロフィール")
            }

            // ドロップダウンメニューを出す　//直接フラグ操作
            DropDownMenu_View(showMenu, onDismiss = { showMenu = false })
        }

    )

}
// タイトルとアイコンE-------------------------------------------------------------------

// アイコン押した時のドロップダウンメニューS-------------------------------------------------
@Composable
fun DropDownMenu_View(showMenu: Boolean, onDismiss: () -> Unit) {
    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = onDismiss
    ) {
        // メッセージ
        DropdownMenuItem(
            text = { Text("メッセージ") },
            onClick = { onDismiss() /*TODO*/ },
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