package studio.kts.android.school.lection4.networking.data

import retrofit2.http.GET
import retrofit2.http.Query
import studio.kts.android.school.lection4.networking.data.models.RemoteUser
import studio.kts.android.school.lection4.networking.data.models.ServerItemsWrapper

interface GithubApi {
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String
    ): ServerItemsWrapper<RemoteUser>
}
