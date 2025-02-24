# DiscoverApi

All URIs are relative to *http://localhost:8080/api*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**discoverFollowsGet**](DiscoverApi.md#discoverFollowsGet) | **GET** /discover/follows | GetLatestFollows |
| [**discoverLatestGet**](DiscoverApi.md#discoverLatestGet) | **GET** /discover/latest | GetLatest |


<a id="discoverFollowsGet"></a>
# **discoverFollowsGet**
> ResponseDiscoverResponse discoverFollowsGet(lastId)

GetLatestFollows

フォローしているユーザーの最新の投稿を取得する

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DiscoverApi()
val lastId : kotlin.String = lastId_example // kotlin.String | 投稿をフィルタするID
try {
    val result : ResponseDiscoverResponse = apiInstance.discoverFollowsGet(lastId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DiscoverApi#discoverFollowsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DiscoverApi#discoverFollowsGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **lastId** | **kotlin.String**| 投稿をフィルタするID | [optional] |

### Return type

[**ResponseDiscoverResponse**](ResponseDiscoverResponse.md)

### Authorization


Configure BearerAuth:
    ApiClient.apiKey["Authorization"] = ""
    ApiClient.apiKeyPrefix["Authorization"] = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="discoverLatestGet"></a>
# **discoverLatestGet**
> ResponseDiscoverResponse discoverLatestGet(lastId)

GetLatest

最新の投稿を取得する

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DiscoverApi()
val lastId : kotlin.String = lastId_example // kotlin.String | 投稿をフィルタするID
try {
    val result : ResponseDiscoverResponse = apiInstance.discoverLatestGet(lastId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DiscoverApi#discoverLatestGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DiscoverApi#discoverLatestGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **lastId** | **kotlin.String**| 投稿をフィルタするID | [optional] |

### Return type

[**ResponseDiscoverResponse**](ResponseDiscoverResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

