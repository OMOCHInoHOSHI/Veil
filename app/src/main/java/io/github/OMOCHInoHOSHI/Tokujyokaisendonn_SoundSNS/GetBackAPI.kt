package io.github.OMOCHInoHOSHI.Tokujyokaisendonn_SoundSNS

import androidx.lifecycle.ViewModel
import org.openapitools.client.apis.UserApi
import org.openapitools.client.apis.FileApi

// APIから取得したユーザー情報を管理するViewModelS-------------------------------------------------
class getAPIViewModel: ViewModel(){

    var uesrname = ""
    var email = ""
    var password = ""
    var user_id = ""

    // ユーザー名を保存
    fun setUserName(name: String){
        this.uesrname = name
    }

    // メアドを保存
    fun setEmail(email: String){
        this.email = email
    }

    // パスワードを保存
    fun setPassword(password: String){
        this.password = password
    }

    // userIDを保存
    fun setUserID(id: String){
        this.user_id = id
    }

    // ユーザー名を取得
    fun getUserName(): String{
        return this.uesrname
    }

    // メアドを取得
    fun getEmail(): String{
        return this.email
    }

    // パスワードを取得
    fun getPassword(): String{
        return this.password
    }

    // userIDを取得
    fun getUserID(): String{
        return this.user_id
    }
}
