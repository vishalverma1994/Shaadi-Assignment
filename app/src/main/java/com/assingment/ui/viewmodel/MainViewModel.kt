package com.assingment.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assingment.data.local.dao.UserDao
import com.assingment.data.local.entities.UserEntity
import com.assingment.data.repository.MainRepository
import com.assingment.utils.NO_INTERNET_CONNECTION_MESSAGE
import com.assingment.utils.NetworkHelper
import com.assingment.utils.Resource
import kotlinx.coroutines.launch


class MainViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    val _userListResponse = MutableLiveData<Resource<LiveData<List<UserEntity>>>>()

    fun requestUserListAPI(resultNumber: Int) {
        viewModelScope.launch {
            _userListResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.requestUserListAPI(resultNumber)
                    .let { response ->
                        if (response.isSuccessful && response.body() != null) {
                            response.body()?.let { result ->
                                mainRepository.saveUsersIntoDB(result.results)
                                _userListResponse.postValue(Resource.success(mainRepository.getUserList()))
                            }
                        } else _userListResponse.postValue(
                            Resource.error(
                                response.body().toString(),
                                null
                            )
                        )
                    }
            } else _userListResponse.postValue(
                Resource.error(
                    NO_INTERNET_CONNECTION_MESSAGE,
                    null
                )
            )
        }
    }

    fun updateUserIntoDB(userEntity: UserEntity) {
        viewModelScope.launch {
            mainRepository.updateUserIntoDB(userEntity)
        }
    }
}