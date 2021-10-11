package studio.kts.android.school.lection4.networking.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteUser(
    @Json(name = "login")
    val username: String,
    @Json(name = "avatar_url")
    val avatar: String?,
    val id: Long
)
