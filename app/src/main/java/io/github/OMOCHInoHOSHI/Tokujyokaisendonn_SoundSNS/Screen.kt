package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS.ui.theme.Select_chatroom
import io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS.ui.theme.VeilTheme

// 画面遷移先S-------------------------
enum class Nav {
//    LoginScreen,//ログイン画面
    HomeScreen, //ホーム画面
    NoticeScreen, //通知画面
    Message_page,   // メッセージ画面
    Select_chatroom,    // チャット選択画面
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
        startDestination = Nav.HomeScreen.name  //ホーム画面を最初に表示
    ) {
        // ルート名：HomeScreen　HomeScreenに遷移
        composable(route = Nav.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
        // ルート名：NoticeScreen　NoticeScreenに遷移
        composable(route = Nav.NoticeScreen.name) {
            NoticeScreen(navController = navController)
        }
        // ルート名：Message_page　Message_page
        composable(route = Nav.Message_page.name) {
            Message_page(navController = navController)
        }
        // ルート名：Select_chatroom　Select_chatroom(仮のルートをn_a作成)
        composable(route = Nav.Select_chatroom.name) {
            Select_chatroom(navController = navController)
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
            // 現在のルートがHomeScreenなら選択状態にする
            selected = currentRoute == Nav.HomeScreen.name,

            onClick = {
                if (currentRoute != Nav.HomeScreen.name) {
                    navController.navigate(Nav.HomeScreen.name)
                }
            }
        )
        // マイクアイコン
        NavigationBarItem(
            icon = { Icon(Icons.Default.Mic, "録音") },
//            label = { Text("録音") },
            selected = selectedTab == 1,
            onClick = { selectedTab = 1 }

            // 画面の練習用(n_a作成)
//            selected = currentRoute == Nav.Select_chatroom.name,
//            onClick = {
//                if (currentRoute != Nav.Select_chatroom.name) {
//                    navController.navigate(Nav.Select_chatroom.name)
//                }
//            }
        )
        // 通知アイコン
        NavigationBarItem(
            icon = { Icon(Icons.Default.Notifications, "通知") },
//            label = { Text("通知") },
            // 現在のルートがNoticeScreenなら選択状態にする
            selected = currentRoute == Nav.NoticeScreen.name,
            onClick = {
                if (currentRoute != Nav.NoticeScreen.name) {
                    navController.navigate(Nav.NoticeScreen.name)
                }
            }
        )
    }
}
// 下のナビゲーションバーセットE----------------------------------------------------------------------------------------
