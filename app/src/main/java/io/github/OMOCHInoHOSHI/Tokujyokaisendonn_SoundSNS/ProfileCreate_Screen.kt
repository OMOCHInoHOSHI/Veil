package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS.ui.theme.VeilTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCreate_Screen(modifier: Modifier = Modifier, navController: NavController) {
    var userName by remember { mutableStateOf("") }
    var userBio by remember { mutableStateOf("") }
    var profileImageUri by remember { mutableStateOf<Uri?>(null) }
    var snsLink by remember { mutableStateOf("") }
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            profileImageUri = uri
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("プロフィール") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) { // 戻るボタン
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = { // 保存ボタン
            FloatingActionButton(onClick = { /*TODO: プロフィール画面への遷移*/ }) {
                Text("保存")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            // プロフィール画像
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .clickable {
                        launcher.launch("image/*")
                    }
            ) {
                if (profileImageUri != null) {
                    AsyncImage(
                        model = profileImageUri,
                        contentDescription = "Profile Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background), // プレースホルダー
                        contentDescription = "Profile Image",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // ユーザー名
            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("ユーザー名") },
                placeholder = { Text("ユーザー名") }, // プレースホルダーを設定
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.width(300.dp)
            )


            Spacer(modifier = Modifier.height(8.dp))

            // SNSリンク (仮)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start // 左端に揃える
            ){
                OutlinedTextField(
                    value = snsLink,
                    onValueChange = { snsLink = it },
                    label = { Text("SNSリンク") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri), // URL入力に適したキーボード
                    modifier = Modifier.width(243.dp)
                )
                Spacer(modifier = Modifier.width(10.dp)) // ボタンとテキストの間にスペースを追加
                IconButton(onClick = { /*TODO: linkの追加*/ }) {
                    Icon(Icons.Default.Add, contentDescription = "linkの追加")
                }
            }


            Spacer(modifier = Modifier.height(8.dp))

            // 一言プロフィール
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start // 左端に揃える
            ) {
                OutlinedTextField(
                    value = userBio,
                    onValueChange = { userBio = it },
                    label = { Text("一言プロフィール") },
                    placeholder = { Text("ユーザー名") }, // プレースホルダーを設定
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.width(300.dp)
                )
            }

            // 音声プロフィール (仮)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("音声プロフィール")
                Spacer(modifier = Modifier.width(20.dp)) // ボタンとテキストの間にスペースを追加
                IconButton(onClick = { /*TODO: 音声プロフィール機能(録音）*/ }) {
                    Icon(Icons.Default.Mic, contentDescription = "音声プロフィール")
                }
                Spacer(modifier = Modifier.width(8.dp)) // マイクアイコンと再生アイコンの間にスペースを追加
                IconButton(onClick = { /*TODO: 音声プロフィール機能(再生)*/ }) {
                    Icon(Icons.Default.PlayArrow, contentDescription = "音声プロフィール再生")
                }
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    VeilTheme {
        ProfileCreate_Screen(navController = NavController(LocalContext.current))
    }
}