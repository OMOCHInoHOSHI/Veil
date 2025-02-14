package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS.ui.theme.VeilTheme


// 通知画面
@Preview(showBackground = true)
@Composable
fun NoticeScreen(){
    Scaffold(){innerPadding ->
        Box(modifier = Modifier.padding(innerPadding))
        Box(){
            Text("通知")
        }
    }


}

