package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS.ui.theme.VeilTheme

// 画面遷移先S-------------------------
enum class Nav {
//    LoginScreen,//ログイン画面
    HomeScreen, //ホーム画面
    NoticeScreen, //通知画面
}
// 画面遷移先E-------------------------

//画面遷移の設定　どこのページへ移動するかnavControllerに定義する
@Composable
fun DisplayNav(){
    // NavControllerを定義
    val navController = rememberNavController()

    // NavHostを作成
    // startDestinationは最初に表示するページ
    NavHost(navController = navController,
        startDestination = Nav.HomeScreen.name  //ホーム画面を最初に表示
    ) {
        composable(route = Nav.HomeScreen.name) {
            HomeScreen(navController = navController
//                onNavigateToConversation = { navController.navigate(Nav.NoticeScreen.name) },
            )
        }
        composable(route = Nav.NoticeScreen.name) { NoticeScreen(navController = navController) }
    }


}


