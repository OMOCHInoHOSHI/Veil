package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import android.util.Log
import androidx.lifecycle.ViewModel
import org.openapitools.client.apis.UserApi
import org.openapitools.client.apis.FileApi

// APIから取得したユーザー情報を管理するViewModelS-------------------------------------------------
class getAPIViewModel: ViewModel(){

    var createdAt:String = ""   //作成時間
    var email: String = ""      //メアド
    var user_id: String = ""    //ユーザーID
    var uesrname: String = ""   //ユーザー名
    var updatedAt:String = ""   //更新時間


    // ユーザー情報を保存
    fun setUserInfo(createdAt: String, getemail: String, getid: String, getname: String, updatedAt: String) {

        this.createdAt = createdAt
        this.email = getemail
        this.user_id = getid
        this.uesrname = getname
        this.updatedAt = updatedAt

        Log.i("FetchUserInfo", "View Email: $getemail, ID: $getid, Name: $getname")
    }

    // useridを取得
    fun get_UserId():String{
        return user_id
    }

    // emailを取得
    fun get_Email():String{
        return email
    }

    // usernameを取得
    fun get_Username():String{
        return uesrname
    }

}
