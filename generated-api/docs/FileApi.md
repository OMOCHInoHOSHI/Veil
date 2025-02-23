# FileApi

All URIs are relative to *http://localhost:8080/api*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**usersUserIdAudiosPost**](FileApi.md#usersUserIdAudiosPost) | **POST** /users/{userId}/audios | UplopadAudio |


<a id="usersUserIdAudiosPost"></a>
# **usersUserIdAudiosPost**
> ResponseAudioUploadResponse usersUserIdAudiosPost(userId, audio, color, tags)

UplopadAudio

ファイルをアップロードする

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = FileApi()
val userId : kotlin.String = userId_example // kotlin.String | ユーザーID
val audio : java.io.File = BINARY_DATA_HERE // java.io.File | 音声ファイル
val color : kotlin.String = color_example // kotlin.String | イメージカラー
val tags : kotlin.String = tags_example // kotlin.String | タグ
try {
    val result : ResponseAudioUploadResponse = apiInstance.usersUserIdAudiosPost(userId, audio, color, tags)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling FileApi#usersUserIdAudiosPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling FileApi#usersUserIdAudiosPost")
    e.printStackTrace()
}
```

### Parameters
| **userId** | **kotlin.String**| ユーザーID | |
| **audio** | **java.io.File**| 音声ファイル | |
| **color** | **kotlin.String**| イメージカラー | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **tags** | **kotlin.String**| タグ | |

### Return type

[**ResponseAudioUploadResponse**](ResponseAudioUploadResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

