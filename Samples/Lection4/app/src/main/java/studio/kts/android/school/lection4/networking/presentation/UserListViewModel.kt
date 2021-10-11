package studio.kts.android.school.lection4.networking.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import studio.kts.android.school.lection4.networking.data.UserRepository
import studio.kts.android.school.lection4.networking.data.models.RemoteUser
import timber.log.Timber

class UserListViewModel: ViewModel() {

    private val repository = UserRepository()

    private val userListLiveData = MutableLiveData<List<RemoteUser>>(emptyList())
    private val isLoadingLiveData = MutableLiveData(false)

    private var currentSearchJob: Job? = null

    val userList: LiveData<List<RemoteUser>>
        get() = userListLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    fun search(query: String) {
        isLoadingLiveData.postValue(true)
        currentSearchJob?.cancel()
        currentSearchJob = viewModelScope.launch {
            runCatching {
                repository.searchUsers(query = query)
            }.onSuccess {
                isLoadingLiveData.postValue(false)
                userListLiveData.postValue(it)
            }.onFailure {
                Timber.e(it)
                isLoadingLiveData.postValue(false)
                userListLiveData.postValue(emptyList())
            }
        }
    }
}
