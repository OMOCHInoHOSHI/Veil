/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package org.openapitools.client.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param createdAt 
 * @param id 
 * @param imageFileName 
 * @param `public` 
 * @param title 
 * @param updatedAt 
 * @param userId 
 */


data class ResponsePlaylistCreateResponse (

    @Json(name = "createdAt")
    val createdAt: kotlin.String,

    @Json(name = "id")
    val id: kotlin.String,

    @Json(name = "imageFileName")
    val imageFileName: kotlin.String,

    @Json(name = "public")
    val `public`: kotlin.Boolean,

    @Json(name = "title")
    val title: kotlin.String,

    @Json(name = "updatedAt")
    val updatedAt: kotlin.String,

    @Json(name = "userId")
    val userId: kotlin.String

) {


}

