# UserApi

All URIs are relative to *http://localhost:8080/api*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**signinPost**](UserApi.md#signinPost) | **POST** /signin | ユーザーのサインインを行う |
| [**signupPost**](UserApi.md#signupPost) | **POST** /signup | ユーザーのサインアップを行う |
| [**usersMeGet**](UserApi.md#usersMeGet) | **GET** /users/me | 自分自身のユーザー情報を取得する |


<a id="signinPost"></a>
# **signinPost**
> ResponseUserSigninResponse signinPost(request)

ユーザーのサインインを行う

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = UserApi()
val request : RequestUserSigninRequest =  // RequestUserSigninRequest | ユーザーログインリクエスト
try {
    val result : ResponseUserSigninResponse = apiInstance.signinPost(request)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling UserApi#signinPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling UserApi#signinPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **request** | [**RequestUserSigninRequest**](RequestUserSigninRequest.md)| ユーザーログインリクエスト | |

### Return type

[**ResponseUserSigninResponse**](ResponseUserSigninResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="signupPost"></a>
# **signupPost**
> ResponseUserSignupResponse signupPost(request)

ユーザーのサインアップを行う

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = UserApi()
val request : RequestUserSignupRequest =  // RequestUserSignupRequest | ユーザーログインリクエスト
try {
    val result : ResponseUserSignupResponse = apiInstance.signupPost(request)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling UserApi#signupPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling UserApi#signupPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **request** | [**RequestUserSignupRequest**](RequestUserSignupRequest.md)| ユーザーログインリクエスト | |

### Return type

[**ResponseUserSignupResponse**](ResponseUserSignupResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="usersMeGet"></a>
# **usersMeGet**
> ResponseUserInfoResponse usersMeGet()

自分自身のユーザー情報を取得する

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = UserApi()
try {
    val result : ResponseUserInfoResponse = apiInstance.usersMeGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling UserApi#usersMeGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling UserApi#usersMeGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ResponseUserInfoResponse**](ResponseUserInfoResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

