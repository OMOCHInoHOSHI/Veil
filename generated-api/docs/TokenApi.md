# TokenApi

All URIs are relative to *http://localhost:8080/api*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**refreshPost**](TokenApi.md#refreshPost) | **POST** /refresh | アクセストークンをリフレッシュする |


<a id="refreshPost"></a>
# **refreshPost**
> ResponseRefreshResponse refreshPost(xRefreshToken)

アクセストークンをリフレッシュする

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = TokenApi()
val xRefreshToken : kotlin.String = xRefreshToken_example // kotlin.String | リフレッシュトークン
try {
    val result : ResponseRefreshResponse = apiInstance.refreshPost(xRefreshToken)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TokenApi#refreshPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TokenApi#refreshPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **xRefreshToken** | **kotlin.String**| リフレッシュトークン | |

### Return type

[**ResponseRefreshResponse**](ResponseRefreshResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

