package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS.ui.theme.VeilTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile_Screen(
    userName: String,
    userBio: String,
    profileImageUrl: String?,
    modifier: Modifier = Modifier,
    navController: NavController
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("プロフィール") },
                actions = {
                    Button(onClick = { /*TODO: 編集画面への遷移*/ }) {
                        Text("編集")
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

            Row( // Rowでプロフィール画像とユーザー情報を横に並べる
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically // Row内で縦方向中央揃え
            ) {
                // プロフィール画像
                if (profileImageUrl != null) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background), // 例: ic_launcher_background をプレースホルダーとして使用
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(8.dp)
                            .clip(CircleShape)
                    )
                } else {
                    // デフォルトの画像やプレースホルダーを表示
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background), // 例: ic_launcher_background をプレースホルダーとして使用
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(8.dp)
                            .clip(CircleShape)
                    )
                }

                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    // ユーザー名
                    Text(text = userName, style = MaterialTheme.typography.headlineSmall)

                    Spacer(modifier = Modifier.height(4.dp))

                    // SNSリンク (仮)
                    Text(text = "XやLINE等のSNSリンク", fontSize = 14.sp, color = Color.Gray)
                    Text(text = "XやLINE等のSNSリンク", fontSize = 14.sp, color = Color.Gray)

                    Spacer(modifier = Modifier.height(8.dp))


                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {// 一言プロフィール
                Text(text = userBio, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("フォロー中 100", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text("フォロワー 100", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // いいね (仮)
            LazyRow {
                items(6) {
                    Box(
                        modifier = Modifier
                            .size(160.dp,80.dp)
                            .padding(8.dp)
                            .background(Color.LightGray) // 仮の背景色
                    ) {
                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text("ユーザー名", fontSize = 12.sp)
                            Text("#タグ", fontSize = 14.sp)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // プレイリスト
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("プレイリスト", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    IconButton(onClick = { /*TODO: 新規プレイリスト作成画面への遷移*/ }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Playlist")
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                LazyRow {
                    items(3) {
                        Column(
                            modifier = Modifier
                                .size(150.dp)
                                .padding(8.dp)
                                .background(Color.LightGray) // 仮の背景色
                                .clickable { /*TODO: プレイリスト詳細画面への遷移など*/ }
                        ) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("プレイリスト名", fontSize = 12.sp)
                            IconButton(onClick = { /*TODO: プレイリスト画面への遷移*/ }) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_launcher_background), // 仮の画像
                                    contentDescription = "Playlist Image",
                                    modifier = Modifier.fillMaxWidth(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    VeilTheme {
        Profile_Screen(
            userName = "特上海鮮どんぶり",
            userBio = "歌手目指してます",
            profileImageUrl = "https://example.com/profile.jpg",
            navController = NavController(LocalContext.current)
        )
    }
}