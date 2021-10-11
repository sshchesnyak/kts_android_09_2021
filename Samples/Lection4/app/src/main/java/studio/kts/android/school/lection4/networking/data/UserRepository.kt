package studio.kts.android.school.lection4.networking.data

import studio.kts.android.school.lection4.networking.data.models.RemoteUser

class UserRepository {

    suspend fun searchUsers(
        query: String,
    ): List<RemoteUser> {
        return Networking.githubApi.searchUsers(query).items
    }
}
