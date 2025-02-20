package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS.ui.theme.VeilTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(modifier: Modifier = Modifier) {
    var userName by remember { mutableStateOf("ユーザー名") }
    var userBio by remember { mutableStateOf("一言プロフィール") }
    var profileImageUri by remember { mutableStateOf<Uri?>(null) }
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
                actions = {
                    IconButton(onClick = { /*TODO: 編集画面への遷移など*/ }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                    }
                }
            )
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
                    .size(100.dp)
                    .clip(CircleShape)
                    .clickable {
                        launcher.launch("image/*")
                    }
            ) {
                if (profileImageUri != null) {
                    AsyncImage(
                        model = profileImageUri,
                        contentDescription = "Profile Image",
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background), // プレースホルダー
                        contentDescription = "Profile Image"
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // ユーザー名
            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("ユーザー名") }
            )

            Spacer(modifier = Modifier.height(4.dp))

            // SNSリンク (仮)
            Text("XやLINE等のSNSリンク", fontSize = 14.sp, color = Color.Gray)
            Text("XやLINE等のSNSリンク", fontSize = 14.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(8.dp))

            // 一言プロフィール
            OutlinedTextField(
                value = userBio,
                onValueChange = { userBio = it },
                label = { Text("一言プロフィール") }
            )

            // 音声プロフィール (仮)
            Button(onClick = { /*TODO: 音声プロフィール機能*/ }) {
                Text("音声プロフィール")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    VeilTheme {
        Profile()
    }
}