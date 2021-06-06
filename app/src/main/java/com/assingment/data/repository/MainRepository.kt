package com.assingment.data.repository

import androidx.lifecycle.LiveData
import com.assingment.data.api.ApiHelper
import com.assingment.data.local.dao.UserDao
import com.assingment.data.local.entities.UserEntity

class MainRepository(private val apiHelper: ApiHelper, private val userDao: UserDao) {
    suspend fun requestUserListAPI(resultNumber: Int) = apiHelper.requestUserList(resultNumber)

    suspend fun saveUsersIntoDB(userList: List<UserEntity>) {
        userDao.insertUser(userList)
    }

    fun getUserList(): LiveData<List<UserEntity>> {
        return userDao.getUserList()
    }

    suspend fun updateUserIntoDB(userEntity: UserEntity) {
        userDao.updateUser(userEntity)
    }

}